package com.gabrieljames85gmail.lastchallenge;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.gabrieljames85gmail.lastchallenge.MainActivity.LOG_TAGS;


/**
 * Created by OSAJI GABRIEL on 23/08/2017.
 */

public final class QueryUtils {



    private QueryUtils(){

    }

    private static ArrayList<Lads> extractfromJson(String JsonFile){

        if (TextUtils.isEmpty(JsonFile)){
            return null;
        }


        ArrayList<Lads> javalags = new ArrayList<>();

        try {
            JSONObject lagObjects = new JSONObject(JsonFile);
            JSONArray objlag = lagObjects.getJSONArray("items");

            for (int i= 0; i<objlag.length(); i++){
                JSONObject currentposition = objlag.getJSONObject(i);
                String name = currentposition.getString("login");
                String avatar = currentposition.getString("avatar_url");
                String profile = currentposition.getString("html_url");

                Lads lad = new Lads(avatar, name , profile);
                javalags.add(lad);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAGS, "Not able to receive data from github..", e);
        }

        return javalags;
    }

    private static URL createUrl(String url){
        URL urls = null;

        try {
            urls    = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAGS, "Problem Creating a Url from this String ..", e );
        }

        return urls;
    }

    private static String readFromStream(InputStream inputstream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputstream != null ){
            InputStreamReader inputStreamReader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null){
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();

    }

    private static String makeHttpRequest(URL url) throws IOException{
        String JsonResponce  = "";

        if (url == null){
            return JsonResponce;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(20000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode()== 200){
                inputStream = urlConnection.getInputStream();
                JsonResponce = readFromStream(inputStream);
            }

        } catch (IOException e) {
            Log.e(LOG_TAGS, "Problem Getting Good Connection  ", e);

        }

        return  JsonResponce;
    }


    public static ArrayList<Lads> fetchJavaDevLag(String urls){

        URL url = createUrl(urls);
        String JsonResponsed = null;

        try {
            JsonResponsed = makeHttpRequest(url);
        }
        catch (IOException e){
            Log.e(LOG_TAGS, " Problem Try to make HttpReques......", e);
        }

        ArrayList<Lads> JavaLags = extractfromJson(JsonResponsed);

        return JavaLags;


    }

}
