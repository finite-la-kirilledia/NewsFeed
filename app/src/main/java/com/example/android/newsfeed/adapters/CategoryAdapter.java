package com.example.android.newsfeed.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.android.newsfeed.CategoryFragment;
import com.example.android.newsfeed.R;

public class CategoryAdapter extends FragmentPagerAdapter {
    private static final String BUSINESS_CATEGORY_URL = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=44b9751fc1544ae3907399f66279543d";
    private static final String BITCOIN_CATEGORY_URL = "https://newsapi.org/v2/everything?q=bitcoin&sortBy=publishedAt&apiKey=44b9751fc1544ae3907399f66279543d";
    private static final String TECHCRUNCH_CATEGORY_URL = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=44b9751fc1544ae3907399f66279543d";
    private static final String APPLE_CATEGORY_URL = "https://newsapi.org/v2/everything?q=apple&sortBy=popularity&apiKey=44b9751fc1544ae3907399f66279543d";
    private static final String WS_JOURNAL_CATEGORY_URL = "https://newsapi.org/v2/everything?domains=wsj.com&apiKey=44b9751fc1544ae3907399f66279543d";

    private static final int BUSINESS_CATEGORY_LOADER_ID = 100;
    private static final int BITCOIN_CATEGORY_LOADER_ID = 200;
    private static final int TECHCRUNCH_CATEGORY_LOADER_ID = 300;
    private static final int APPLE_CATEGORY_LOADER_ID = 400;
    private static final int WS_JOURNAL_CATEGORY_LOADER_ID = 500;

    private Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return createCategoryFragment(WS_JOURNAL_CATEGORY_LOADER_ID, WS_JOURNAL_CATEGORY_URL);
            case 1:
                return createCategoryFragment(BUSINESS_CATEGORY_LOADER_ID, BUSINESS_CATEGORY_URL);
            case 2:
                return createCategoryFragment(APPLE_CATEGORY_LOADER_ID, APPLE_CATEGORY_URL);
            case 3:
                return createCategoryFragment(BITCOIN_CATEGORY_LOADER_ID, BITCOIN_CATEGORY_URL);
            case 4:
                return createCategoryFragment(TECHCRUNCH_CATEGORY_LOADER_ID, TECHCRUNCH_CATEGORY_URL);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    private CategoryFragment createCategoryFragment(int id, String url) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putString("url", url);

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.ws_journal);
            case 1:
                return mContext.getResources().getString(R.string.business);
            case 2:
                return mContext.getResources().getString(R.string.apple);
            case 3:
                return mContext.getResources().getString(R.string.bitcoin);
            case 4:
                return mContext.getResources().getString(R.string.techcrunch);
            default:
                return null;
        }
    }
}
