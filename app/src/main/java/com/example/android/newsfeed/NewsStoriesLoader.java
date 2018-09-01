package com.example.android.newsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.newsfeed.utils.NetworkUtils;

import java.util.List;

public class NewsStoriesLoader extends AsyncTaskLoader<List<NewsStory>> {
    public NewsStoriesLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<NewsStory> loadInBackground() {
        List<NewsStory> newsStories = NetworkUtils.fetchNewsStories("https://newsapi.org/v2/everything?q=apple&sortBy=popularity&apiKey=44b9751fc1544ae3907399f66279543d");
        return newsStories;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
