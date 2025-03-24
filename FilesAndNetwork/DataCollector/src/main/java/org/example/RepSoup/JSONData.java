package org.example.RepSoup;

public class JSONData {
    public String stationName;
    public String Depth;

    public JSONData(String stationName, String depth) {
        this.stationName = stationName;
        Depth = depth;
    }

    @Override
    public String toString() {
        return "JSONData{" +
                "stationName='" + stationName + '\'' +
                ", Depth='" + Depth + '\'' +
                '}';
    }
}
