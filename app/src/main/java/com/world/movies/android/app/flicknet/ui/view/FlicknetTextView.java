package com.world.movies.android.app.flicknet.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.world.movies.android.app.flicknet.infraestructure.FontCache;

/**
 * Created by root on 9/4/16.
 */
public class FlicknetTextView extends TextView {

    public FlicknetTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public FlicknetTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public FlicknetTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    public FlicknetTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Raleway-Medium.ttf", context);
        setTypeface(customFont);
    }
}
