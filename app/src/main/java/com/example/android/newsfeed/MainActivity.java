package com.example.android.newsfeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsStory>> {

    private ListView mListView;
    private NewsStoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list);
        mAdapter = new NewsStoryAdapter(this, new ArrayList<NewsStory>());
        mListView.setAdapter(mAdapter);
    }

    @NonNull
    @Override
    public Loader<List<NewsStory>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new NewsStoriesLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsStory>> loader, List<NewsStory> newsStories) {
        mAdapter.clear();
        if (newsStories != null && !newsStories.isEmpty()) {
            mAdapter.addAll(newsStories);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsStory>> loader) {
        mAdapter.clear();
    }
}
