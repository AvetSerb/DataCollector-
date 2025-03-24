package org.example.RepSoup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class RepFounder {
    public String folderPath;
    List<File> csvFiles = new ArrayList<>();
    List<File> jsonFiles = new ArrayList<>();

    public RepFounder(String folderPath) {
        this.folderPath = folderPath;
        File path = new File(folderPath);
        getRecursiveFile(path);

    }
    private void getRecursiveFile(File folder){
        File[] files = folder.listFiles();
        assert files != null;
        for(File file : files){
            String s = "";
            int lastDotIndex = file.getName().lastIndexOf('.');
            if (lastDotIndex > 0) {
                s = file.getName().substring(lastDotIndex + 1);
                System.out.println(s);
            }
            if(s.equals("csv")){
                csvFiles.add(file);
                System.out.println(file.getName());
            }
            if(s.equals("json")){
                jsonFiles.add(file);
                System.out.println(file.getName());
            }
            if(file.isDirectory()){
                getRecursiveFile(file);
            }
        }
    }
    public List<CSVData> parseAllCSVFiles(){
        List<CSVData> res = new ArrayList<>();
        for(File data : csvFiles){
            res.addAll(parseCSV(data));
        }
        return res;
    }

    public List<JSONData> parseAllJSONFiles() throws IOException {
        List<JSONData> res = new ArrayList<>();
        for(File data : jsonFiles){
            List<JSONData> list = parseJSON(data);
            if(list != null){
                res.addAll(list);
            }
        }
        return res;
    }
    private List<CSVData> parseCSV(File file){
        try {
            List<CSVData> list = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] tmp = line.split(",");
                if(tmp.length == 2){
                    list.add(new CSVData(tmp[0], tmp[1]));
                }
            }
            reader.close();
            return list;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
    }
    private List<JSONData> parseJSON(File file) throws IOException {
        try {
            List<JSONData> result = new ArrayList<>();
            String jsonString = new String(Files.readAllBytes(Paths.get(file.getPath())));

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject stationObject = jsonArray.getJSONObject(i);
                String stationName = stationObject.getString("station_name");
                String depth = stationObject.getString("depth");
                result.add(new JSONData(stationName, depth));
            }
            return result;
        } catch (Exception e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
    }
}
