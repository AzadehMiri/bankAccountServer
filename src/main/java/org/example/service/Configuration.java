package org.example.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Configuration {
    public static int readPort(String filePath) {
        JSONParser parser = new JSONParser();
        int port = 0;
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
            String portNumber =  jsonObject.get("port").toString();
            port = Integer.parseInt(portNumber);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return port;
    }
}
