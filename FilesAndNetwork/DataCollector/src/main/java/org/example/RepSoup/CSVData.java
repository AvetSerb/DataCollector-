package org.example.RepSoup;

public class CSVData {
    public String name;
    public String data;

    public CSVData(String name, String data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CSVData{" +
                "name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
