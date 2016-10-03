package com.world.movies.android.app.flicknet.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.world.movies.android.app.flicknet.R;
import com.world.movies.android.app.flicknet.model.TrailerItem;
import com.world.movies.android.app.flicknet.ui.PlayerActivity;

/**
 * Created by eltonjhony on 22/09/16.
 */

public class CustomGridTrailerViewAdapter extends ArrayAdapter<TrailerItem>
        implements View.OnClickListener{

    private String mVideoKey;
    private int mResource;

    public CustomGridTrailerViewAdapter(Context context, int resource) {
        super(context, resource);
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoViewHolder holder = new VideoViewHolder();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
            holder.thumbnailView = (ImageView) convertView.findViewById(R.id.video_thumbnail);
            holder.titleView = (TextView) convertView.findViewById(R.id.video_title);
            convertView.setTag(holder);
        } else {
            holder = (VideoViewHolder) convertView.getTag();
        }

        TrailerItem trailerItem = getItem(position);
        mVideoKey = trailerItem.getKey();
        Picasso.with(getContext())
                .load(trailerItem.getThumbnail())
                .into(holder.thumbnailView);
        holder.titleView.setText(trailerItem.getName());

        convertView.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent ytPlayerIntent = new Intent(getContext(), PlayerActivity.class);
        ytPlayerIntent.putExtra(getContext().getString(R.string.VIDEO_TAG), mVideoKey);
        getContext().startActivity(ytPlayerIntent);
    }

    static class VideoViewHolder {
        public ImageView thumbnailView;
        public TextView titleView;
    }
}
