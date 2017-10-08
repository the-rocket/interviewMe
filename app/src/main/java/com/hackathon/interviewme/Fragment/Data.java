package com.hackathon.interviewme.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yernar on 07/10/2017.
 */

public class Data {
    public static JSONObject data = new JSONObject();
    static String[] company = {"Google", "Facebook", "Twitter", "MLH", "Microsoft", "Dell", "ComlainInt", "Apple"};
    static String[] person = {"Sundar Pichai", "Mark Zuckerberg", "Jack Dorsey", "Nick", "Bill Gates", "Daniyar Kaiyrbolatov","Dimash Kazakh","Tim Cook"};
    static String message = "";

    Data() {

    }
    static {
        JSONArray item = new JSONArray();
        for(int i = 0; i < 8; ++ i) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("company", company[i]);
                jsonObject.put("person", person[i]);
                message = "Hi Mr Kaiyrbolatov,<br>" + "My name is <b>" + person[i] + "</b> we glad to invite you to our " + company[i] + " company" +
                    "<br>" +
                    "I recently got you resume. As we are open for various internship positions, I will be glad to have a Skype conversation to discuss the internship terms and duties. If it fits for both parties next summer we may work together." +
                    "<br>" +
                    "Besides, we are open for remote contribution to our projects which rely on machine learning and natural language processing foundations. If you want to be involved in such an effort during the education season, we may discuss this too.<br>"+
                    "<b>Let me ask some interview question, press the button bellow</b>";
                jsonObject.put("message", message);
                jsonObject.put("answered", false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            item.put(jsonObject);
        }
        try {
            data.put("jobs", item);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

