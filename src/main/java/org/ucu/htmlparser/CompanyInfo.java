package org.ucu.htmlparser;

import lombok.*;

import java.util.ArrayList;

@Data
public class CompanyInfo {
    public ArrayList<String> logos;
    public ArrayList<String> icons;
    public String name;
    public String url;

    public CompanyInfo(){
        name = "";
        url = "";
        logos = new ArrayList<>();
        icons = new ArrayList<>();
    }
}
