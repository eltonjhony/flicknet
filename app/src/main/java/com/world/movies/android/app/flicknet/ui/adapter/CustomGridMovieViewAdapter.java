package com.world.movies.android.app.flicknet.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.world.movies.android.app.flicknet.R;
import com.world.movies.android.app.flicknet.model.MovieMainInfo;

import java.util.List;

/**
 * Created by root on 8/28/16.
 */
public class CustomGridMovieViewAdapter extends ArrayAdapter<MovieMainInfo> {

    private int mResource;

    public CustomGridMovieViewAdapter(Context context, int resource) {
        super(context, resource);
        this.mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MovieHolder holder = new MovieHolder();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
            holder.imagePoster = (ImageView) convertView.findViewById(R.id.imageViewMovie);
            convertView.setTag(holder);
        } else {
            holder = (MovieHolder) convertView.getTag();
        }

        MovieMainInfo item = getItem(position);
        Picasso.with(getContext()).load(item.getPosterPath()).into(holder.imagePoster);

        return convertView;
    }

    public void addNewItems(List<MovieMainInfo> items) {
        this.addAll(items);
        notifyDataSetChanged();
    }

    static class MovieHolder {
        ImageView imagePoster;
    }
}
