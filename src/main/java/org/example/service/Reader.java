package org.example.service;

import org.example.model.Deposit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List<Deposit> jsonParser(String path) {
        JSONParser parser = new JSONParser();
        List<Deposit> depositList = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(path));
            System.out.println("deposits");
            JSONArray subjects = (JSONArray) jsonObject.get("deposits");
            for (int i = 0; i < subjects.size(); i++) {
                Deposit deposit = new Deposit();
                deposit.setCustomer(((JSONObject) subjects.get(i)).get("customer").toString());
                deposit.setId(Long.parseLong(((JSONObject) subjects.get(i)).get("id").toString()));
                deposit.setInitialBalance(Integer.parseInt(((JSONObject) subjects.get(i)).get("initialBalance").toString()));
                deposit.setUpperBound(Integer.parseInt(((JSONObject) subjects.get(i)).get("upperBound").toString()));

                depositList.add(deposit);
            }
            //System.out.println(depositList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return depositList;
    }
}

