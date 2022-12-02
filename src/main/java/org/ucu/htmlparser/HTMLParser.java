package org.ucu.htmlparser;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HTMLParser {


    static void getLogoElementFromLinkTag(Document doc, ArrayList<String> logos, ArrayList<String> icons) {
        // Finds logos in link tags in the document, and provides them to CompanyInfo lists
        Elements result = doc.head().select("link[rel]");
        if (result.first() != null){
            for(Element item : result) {
                String attribute = item.attr("rel");
                if (attribute.contains("icon")) {
                    icons.add(item.attr("href"));
                } else if (attribute.contains("logo")) {
                    logos.add(item.attr("href"));
                }
            }
        }
    }
    static void getLogoElementFromImgTag(Document doc, ArrayList<String> logos, ArrayList<String> icons) {
        // Finds logos in img tags in the document, and provides them to CompanyInfo lists
        Elements result = doc.select("img[src]");
        if (result.first() != null){
            for(Element item : result){
                String attribute = item.attr("src");
                if(attribute.contains("logo")){
                    logos.add(attribute);
                }else if(attribute.contains("icon")){
                    icons.add(attribute);
                }
            }
        }
    }

    public static CompanyInfo parse(String url, CompanyInfo info){
        try {
            final Document document = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:107.0) Gecko/20100101 Firefox/107.0")
                    .timeout(30000)
                    .get();
            getLogoElementFromLinkTag(document, info.logos, info.icons);
            getLogoElementFromImgTag(document, info.logos, info.icons);

        }catch (Exception e){
            System.out.println("Unable to retrieve data from URL: " + url);
            System.out.println(e);
        }
        return info;
    }

    static void test(){
        CompanyInfo info = new CompanyInfo();
        HTMLParser.parse("https://ucu.edu.ua", info);
        HTMLParser.parse("https://twitter.com", info);
        HTMLParser.parse("https://google.com.ua/", info);
        HTMLParser.parse("https://www.facebook.com", info);
        HTMLParser.parse("https://www.linkedin.com", info);
        HTMLParser.parse("https://www.instagram.com", info);
        HTMLParser.parse("https://www.youtube.com", info);
        HTMLParser.parse("https://www.reddit.com", info);
        HTMLParser.parse("https://www.tumblr.com", info);
    }
}
