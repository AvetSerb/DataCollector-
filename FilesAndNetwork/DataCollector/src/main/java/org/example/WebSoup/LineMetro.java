package org.example.WebSoup;

import java.util.ArrayList;
import java.util.List;

public class LineMetro {
    public String lineName;
    public String numberLine;

    public List<StantionMetro> stantionMetroList = new ArrayList<>();

    public LineMetro(String lineName, String numberLine) {
        this.lineName = lineName;
        this.numberLine = numberLine;
    }
}
