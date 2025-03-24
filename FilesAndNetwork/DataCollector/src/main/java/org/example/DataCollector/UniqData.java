package org.example.DataCollector;

public class UniqData {
    public String name;
    public String line;
    public String date;
    public String depth;
    public String hasConnection;

    public UniqData() {
    }

    public String toJSONString(){
        String res = "{\n" + "\"name\": \""+ name + "\"";
        if(line != null){
            res += ",\n\"line\": \""+ line + "\"";
        }
        if(date != null){
            res += ",\n\"date\": \""+ date + "\"";
        }
        if(depth != null){
            res += ",\n\"depth\": \""+depth + "\"";
        }
        if(hasConnection != null){
            res += ",\n\"hasConnection\": \""+hasConnection + "\"";
        }
        res += "},\n";
        return res;
    }
}
