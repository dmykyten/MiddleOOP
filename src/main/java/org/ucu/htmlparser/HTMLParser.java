package org.ucu.htmlparser;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HTMLParser {
    private String url;

    CompanyInfo getLogoElementFromLinkTag(Document doc, CompanyInfo info) {
        Elements result = doc.head().select("link[rel]");
        if (result.first() != null){
            // System.out.println("debug");
            // System.out.println(logos);
            for(Element item : result) {
                String attribute = item.attr("rel");
                if (attribute.contains("icon")) {
                    info.icons.add(item.attr("href"));
                } else if (attribute.contains("logo")) {
                    info.logos.add(item.attr("href"));
                }
            }
            return info;
        }
        return null;
    }
    CompanyInfo getLogoElementFromImgTag(Document doc, CompanyInfo info) {
        Elements result = doc.select("img[src]");
        /*System.out.println();
        System.out.println(logos);
        System.out.println();*/
        if (result.first() != null){
            // System.out.println("debug");
            // System.out.println(logos);
            for(Element item : result){
                String attribute = item.attr("src");
                if(attribute.contains("logo")){
                    info.logos.add(attribute);
                }else if(attribute.contains("icon")){
                    info.icons.add(attribute);
                }
            }
        }
        return info;
    }

    public void parse(String url){

        this.url = url;
        CompanyInfo i = new CompanyInfo();
        try {
            final Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:107.0) Gecko/20100101 Firefox/107.0")
                    .timeout(30000)
                    .get();
            System.out.println(url.concat(" link tag"));
            System.out.println(getLogoElementFromLinkTag(document, i));
            System.out.println(url.concat(" img tag"));
            System.out.println(getLogoElementFromImgTag(document, i));
            ArrayList<String> logos = new ArrayList<String>();

        }catch (Exception e){
            System.out.println("Unable to retrieve data from URL: " + url);
            System.out.println(e);
        }finally {
            this.url = null;
        }

    }
    public static void main(String[] args){
        HTMLParser parser = new HTMLParser();
        parser.parse("https://twitter.com");
        parser.parse("https://google.com.ua/");
        parser.parse("https://www.facebook.com");
        parser.parse("https://www.linkedin.com");
        parser.parse("https://www.instagram.com");
        parser.parse("https://www.youtube.com");
        parser.parse("https://www.reddit.com");
        parser.parse("https://www.tumblr.com");
    }
}
