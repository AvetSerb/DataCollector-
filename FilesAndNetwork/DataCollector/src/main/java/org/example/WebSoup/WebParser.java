package org.example.WebSoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebParser {
    public Document page;
    public WebParser(String url){
        try{
            page = Jsoup.connect(url).get();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public String getText(){
        return page.title() + page.text();
    }

    public List<LineMetro> getLines(){
        List<LineMetro> result = new ArrayList<>();
        Elements elements = page.select(".js-toggle-depend");
        for (Element el : elements){
            Pattern pattern = Pattern.compile(".*-(.*)");
            Matcher matcher = pattern.matcher(el.attribute("data-depend").getValue());
            if (matcher.find()) {
                String extractedSubstring = matcher.group(1);
                result.add(new LineMetro(el.text(), extractedSubstring.substring(0, extractedSubstring.length() - 2)));
            } else {
                System.out.println("Substring not found.");
            }
        }
        return null;
    }
    public List<StantionMetro> getStantion(){
        List<StantionMetro> result = new ArrayList<>();
        Elements subElements = page.getAllElements().select(".single-station");
        for(Element subEl : subElements){
            Elements elements = subEl.select(".t-icon-metroln");
            StantionMetro stantionMetro = new StantionMetro(subEl.select(".name").text());
            for(Element e : elements){
                Set<String> classesSet = e.classNames();
                classesSet.remove("t-icon-metroln");
                String numberStr = classesSet.toString().replaceAll("[^0-9]", "");
                int number = Integer.parseInt(numberStr);
                stantionMetro.addNumberOfStantion(number);
            }
            result.add(stantionMetro);
        }
        return result;
    }
}
