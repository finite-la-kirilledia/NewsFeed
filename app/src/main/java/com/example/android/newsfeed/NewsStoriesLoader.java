package com.example.android.newsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.newsfeed.utils.NetworkUtils;

import java.util.List;

public class NewsStoriesLoader extends AsyncTaskLoader<List<NewsStory>> {
    private String mUrl;

    public NewsStoriesLoader(@NonNull Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Nullable
    @Override
    public List<NewsStory> loadInBackground() {
        List<NewsStory> newsStories = NetworkUtils.fetchNewsStories(mUrl);
        return newsStories;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
