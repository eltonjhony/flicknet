package com.world.movies.android.app.flicknet.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import com.world.movies.android.app.flicknet.R;
import com.world.movies.android.app.flicknet.ui.MoviesFragment;

/**
 * Created by root on 9/4/16.
 */
public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MoviesFragment.newInstance(mContext.getString(R.string.pref_order_popular));
            case 1:
                return MoviesFragment.newInstance(mContext.getString(R.string.pref_order_top_rated));
            case 2:
                return MoviesFragment.newInstance(mContext.getString(R.string.pref_order_now_playing));
            case 3:
                return MoviesFragment.newInstance(mContext.getString(R.string.pref_order_upcoming));
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
