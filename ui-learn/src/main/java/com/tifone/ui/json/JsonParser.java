package com.tifone.ui.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tongkao.chen on 2018/4/19.
 */

public class JsonParser {
    public JsonParser() {

    }
    public String toJsonObjectString(JsonBean target) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", target.getName());
            jsonObject.put("summary", target.getSummary());
            jsonObject.put("description", target.getDescription());
            jsonObject.put("address", target.getAddress());
            jsonObject.put("distance", target.getDistance());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    public JsonBean parserJsonObject(String json) {
        JSONObject jsonObject = null;
        JsonBean bean = null;
        try {
            jsonObject = new JSONObject(json);
            String name = jsonObject.getString("name");
            String summary = jsonObject.getString("summary");
            String description = jsonObject.getString("description");
            String address = jsonObject.getString("address");
            int distance = jsonObject.getInt("distance");
            bean = new JsonBean(name, summary, description, address, distance);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public String toJsonArrayString(List<JsonBean> target) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (JsonBean bean : target) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", bean.getName());
                jsonObject.put("summary", bean.getSummary());
                jsonObject.put("description", bean.getDescription());
                jsonObject.put("address", bean.getAddress());
                jsonObject.put("distance", bean.getDistance());
                jsonArray.put(jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray.toString();
    }
    public List<JsonBean> parserJsonArray(String json) {
        List<JsonBean> mList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object != null) {
                    String name = object.getString("name");
                    String summary = object.getString("summary");
                    String description = object.getString("description");
                    String address = object.getString("address");
                    int distance = object.getInt("distance");
                    JsonBean bean = new JsonBean(name, summary, description, address, distance);
                    mList.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mList;
    }

    public JsonBean useGsonToParserObjectString(String json) {
        Gson gson = new Gson();
        JsonBean bean = gson.fromJson(json, JsonBean.class);
        return bean;
    }

    public List<JsonBean> useGsonToParserArrayString(String json) {
        Gson gson = new Gson();
        List<JsonBean> list = gson.fromJson(json, new TypeToken<List<JsonBean>>(){}.getType());
        return list;
    }

    public String useGsonToParserBean(JsonBean bean) {
        Gson gson = new Gson();
        String json = gson.toJson(bean);
        return json;
    }
    public String useGsonToParserArrayBean(List<JsonBean> beans) {
        Gson gson = new Gson();
        String json = gson.toJson(beans, new TypeToken<List<JsonBean>>(){}.getType());
        return json;
    }
}
