package com.example.android.newsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewsStoryAdapter extends ArrayAdapter<NewsStory> {
    public NewsStoryAdapter(@NonNull Context context, List<NewsStory> newsStories) {
        super(context, 0, newsStories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        NewsStory newsStory = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        CircleImageView imageView = convertView.findViewById(R.id.image);
        if (!newsStory.getImageUrl().equals("null")) {
            Picasso.get().load(newsStory.getImageUrl()).into(imageView);
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        TextView titleTextView = convertView.findViewById(R.id.title);
        titleTextView.setText(newsStory.getTitle());

        TextView pubDateTextView = convertView.findViewById(R.id.pub_date);
        pubDateTextView.setText(formatPubDate(newsStory.getPubDate()));

        return convertView;
    }

    private String formatPubDate(String pubDate) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        Date date;
        try {
            date = parser.parse(pubDate);
        } catch (ParseException e) {
            e.getStackTrace();
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm, dd MMMM yyyy", Locale.US);
        return formatter.format(date);
    }
}
