package org.example.WebSoup;

import java.util.ArrayList;
import java.util.List;
public class StantionMetro {
    public String name;
    public List<Integer> numbersOfLine = new ArrayList<>();

    public StantionMetro(String name) {
        this.name = name;
    }

    public void addNumberOfStantion(int number){
        numbersOfLine.add(number);
    }
}

