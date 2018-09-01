package com.example.android.newsfeed;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.newsfeed.adapters.NewsStoryAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsStory>> {
    private ListView mListView;
    private NewsStoryAdapter mAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_category, container, false);

        mListView = parentView.findViewById(R.id.list);
        mAdapter = new NewsStoryAdapter(getContext(), new ArrayList<NewsStory>());
        mListView.setAdapter(mAdapter);

        getActivity().getSupportLoaderManager().initLoader(
                CategoryFragment.this.getArguments().getInt("id"), null, CategoryFragment.this);

        return parentView;
    }

    @NonNull
    @Override
    public Loader<List<NewsStory>> onCreateLoader(int i, @Nullable Bundle bundle) {
        String url = CategoryFragment.this.getArguments().getString("url");
        return new NewsStoriesLoader(getContext(), url);
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