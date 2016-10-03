package com.world.movies.android.app.flicknet.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.world.movies.android.app.flicknet.R;
import com.world.movies.android.app.flicknet.ui.adapter.TabPagerAdapter;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    private ActionBar mActionBar;
    private ViewPager mViewPager;
    private TabPagerAdapter mTabPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.advance_preferences, false);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(1000);

        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        String [] tabs = new String[]{getString(R.string.category_popular),
                getString(R.string.category_top_rated),
                getString(R.string.category_now_playing),
                getString(R.string.category_upcoming)};
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Regular.ttf");

        defineActionBarTabView(tabs, typeFace);

        mTabPageAdapter = new TabPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(mTabPageAdapter);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mActionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
    }

    private void defineActionBarTabView(String[] tabs, Typeface typeFace) {
        for(String tab : tabs) {
            TextView t = new TextView(this);
            t.setAllCaps(true);
            t.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER_VERTICAL));
            t.setGravity(Gravity.CENTER_VERTICAL);
            t.setTextColor(Color.WHITE);
            t.setText(tab);
            t.setTypeface(typeFace);

            mActionBar.addTab(mActionBar.newTab()
                    .setCustomView(t)
                    .setTabListener(this));
        }
    }
}
