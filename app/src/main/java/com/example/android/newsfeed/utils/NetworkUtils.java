package com.example.android.newsfeed.utils;

import android.text.TextUtils;

import com.example.android.newsfeed.NewsStory;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {

    public static List<NewsStory> fetchNewsStories(String stringUrl) {
        URL url = createUrl(stringUrl);
        String jsonResponse = makeHttpsRequest(url);
        List<NewsStory> newsStories = extractFeaturesFromJson(jsonResponse);

        return newsStories;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.getStackTrace();
        }

        return url;
    }

    private static String makeHttpsRequest(URL url) {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpsURLConnection connection = null;
        InputStream inputStream = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.connect();

            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
        } catch (IOException e) {
            e.getStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }

        return jsonResponse;
    }

    private static List<NewsStory> extractFeaturesFromJson(String jsonResponse) {
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<NewsStory> newsStories = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray articles = root.getJSONArray("articles");
            for (int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.getJSONObject(i);

                String title = article.getString("title");
                String pubDate = article.getString("publishedAt");
                String imageUrl = article.getString("urlToImage");

                NewsStory newsStory = new NewsStory(title, pubDate, imageUrl);
                newsStories.add(newsStory);
            }
        } catch (JSONException e) {
            e.getStackTrace();
        }

        return newsStories;
    }

    private static String readFromStream(InputStream inputStream) {
        try {
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.getStackTrace();
            return "";
        }
    }
}
