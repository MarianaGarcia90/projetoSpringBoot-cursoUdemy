package com.udemy.projeto.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static String decodeParam(String s){
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> decodeIntList(String s){
        String[] vector = s.split(",");
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<vector.length; i++){
            list.add(Integer.parseInt(vector[i]));
        }
        return list;
    }
}
