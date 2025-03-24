package org.example.DataCollector;

import org.example.RepSoup.CSVData;
import org.example.RepSoup.JSONData;
import org.example.WebSoup.StantionMetro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class DataCollector {
    List<UniqData> res = new ArrayList<>();
    public DataCollector collect(List<CSVData> csvData, List<JSONData> jsonData, List<StantionMetro> stantionsMetro){



        for(StantionMetro s : stantionsMetro){

            UniqData unq = new UniqData();
            unq.name = s.name;
            unq.line = (s.numbersOfLine.isEmpty()) ? null :  s.numbersOfLine.get(0).toString();
            unq.hasConnection = Boolean.toString(s.numbersOfLine.size() > 1);

            for (CSVData csv : csvData){
                if(csv.name == s.name){
                    unq.date = csv.data;
                    break;
                }
            }

            for (JSONData json : jsonData){
                if(json.stationName == s.name){
                    unq.depth = json.Depth;
                    break;
                }
            }

            System.out.println(unq.toJSONString());
            res.add(unq);
        }
        return this;
    }

    public void toJSON(String filepath){
        String s = "{\n\"stations\": [\n";
        for(UniqData uniq : res){
            s += uniq.toJSONString();
        }
        s = s.substring(0, s.length() - 2);
        s += "\n]\n}";
        System.out.println(s);
        try {
            Files.writeString(Path.of(filepath + ".json"), s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

