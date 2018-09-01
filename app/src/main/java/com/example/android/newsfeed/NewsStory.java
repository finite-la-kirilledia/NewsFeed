package com.example.android.newsfeed;

public class NewsStory {
    private String mTitle;
    private String mPubDate;
    private String mTmageUrl;

    public NewsStory(String title, String pubDate, String imageUrl) {
        this.mTitle = title;
        this.mPubDate = pubDate;
        this.mTmageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public String getImageUrl() {
        return mTmageUrl;
    }
}
