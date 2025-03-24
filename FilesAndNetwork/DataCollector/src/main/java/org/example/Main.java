package org.example;

import org.example.DataCollector.DataCollector;
import org.example.RepSoup.RepFounder;
import org.example.WebSoup.WebParser;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        WebParser wp = new WebParser("https://skillbox-java.github.io/");
        wp.getLines();


        RepFounder rf = new RepFounder("C:\\Users\\Пк\\Desktop\\test");
        System.out.println(rf.parseAllCSVFiles());
        System.out.println(rf.parseAllJSONFiles());

        new DataCollector().collect(rf.parseAllCSVFiles(), rf.parseAllJSONFiles(), wp.getStantion()).toJSON("myFile");

    }
}
