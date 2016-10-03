package com.world.movies.android.app.flicknet.ui;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.world.movies.android.app.flicknet.R;
import com.world.movies.android.app.flicknet.manager.FetchMovieDetailManager;
import com.world.movies.android.app.flicknet.manager.OnLoadMovieListener;
import com.world.movies.android.app.flicknet.manager.OnLoadTranslateTextListener;
import com.world.movies.android.app.flicknet.manager.TranslateTextManager;
import com.world.movies.android.app.flicknet.model.MovieDetailView;
import com.world.movies.android.app.flicknet.model.MovieMainInfo;
import com.world.movies.android.app.flicknet.model.TrailerItem;

import java.util.List;

/**
 * Created by root on 9/4/16.
 */
public class DetailFragment extends Fragment implements OnLoadMovieListener,
        OnLoadTranslateTextListener, PopupMenu.OnMenuItemClickListener {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private LinearLayout mDetailLayoutContainer;
    private View mRootView;

    private MovieMainInfo mMovieMainInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_detail, container, false);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) getActivity().findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbarLayout.setVisibility(View.INVISIBLE);

        mDetailLayoutContainer = (LinearLayout) mRootView.findViewById(R.id.detail_layout_container);
        mDetailLayoutContainer.setVisibility(View.INVISIBLE);

        mMovieMainInfo = (MovieMainInfo) getActivity()
                .getIntent()
                .getSerializableExtra(Intent.EXTRA_TEXT);

        getChildFragmentManager().beginTransaction()
                .add(R.id.container_trailer, TrailerFragment.newInstance(mMovieMainInfo.getId()))
                .commit();

        FetchMovieDetailManager movieDetailManager = new FetchMovieDetailManager();
        movieDetailManager.setOnLoadMovieListener(this);
        movieDetailManager.execute(mMovieMainInfo.getId());

        return mRootView;
    }

    @Override
    public void onLoadMovieDetail(MovieDetailView modelView) {

        ImageView imageViewPoster = (ImageView) mRootView.findViewById(R.id.poster_image_view);
        Picasso.with(getActivity()).load(mMovieMainInfo.getPosterPath()).into(imageViewPoster);

        TextView textViewReleaseDate = (TextView) mRootView.findViewById(R.id.view_release_date);
        textViewReleaseDate.setText(modelView.getReleaseDate());

        TextView textViewPopularity = (TextView) mRootView.findViewById(R.id.view_popularity);
        textViewPopularity.setText(modelView.getPopularityConverted());

        TextView textViewVoteCount = (TextView) mRootView.findViewById(R.id.view_vote_count);
        textViewVoteCount.setText(modelView.getVoteCount());

        RatingBar ratingBarVoteAverage = (RatingBar) mRootView.findViewById(R.id.view_vote_average);
        ratingBarVoteAverage.setRating(modelView.getVoteAverageAsFloat());

        TextView textViewResume = (TextView) mRootView.findViewById(R.id.summary_text_view);
        textViewResume.setText(modelView.getOverview());

        setLayoutTitle(modelView.getOriginalTitle());

        final ImageView image = (ImageView) getActivity().findViewById(R.id.image);
        Picasso.with(getActivity()).load(modelView.getBackdropPath()).into(image, new Callback() {
            @Override public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(palette -> applyPalette(palette));
            }
            @Override public void onError() {
            }
        });
        mCollapsingToolbarLayout.setVisibility(View.VISIBLE);
        mDetailLayoutContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadMovieTrailerDetail(List<TrailerItem> trailerItems) {}

    @Override
    public void onTranslateTextListener(String translatedText) {
        TextView textViewResume = (TextView) mRootView.findViewById(R.id.summary_text_view);
        textViewResume.setText(translatedText);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detail_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case (R.id.translate):
                View menuItemView = getActivity().findViewById(itemId);
                PopupMenu popupMenu = new PopupMenu(getActivity(), menuItemView);
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.english:
                translate(getString(R.string.lang_en));
                return true;
            case R.id.portuguese:
                translate(getString(R.string.lang_pt));
                return true;
            case R.id.italy:
                translate(getString(R.string.lang_it));
                return true;
            case R.id.spanish:
                translate(getString(R.string.lang_es));
                return true;
            case R.id.chinese:
                translate(getString(R.string.lang_zh));
                return true;
            case R.id.german:
                translate(getString(R.string.lang_de));
                return true;
            case R.id.russian:
                translate(getString(R.string.lang_ru));
                return true;
            case R.id.french:
                translate(getString(R.string.lang_fr));
                return true;
            case R.id.japanese:
                translate(getString(R.string.lang_ja));
                return true;
            case R.id.polish:
                translate(getString(R.string.lang_pl));
                return true;
        }
        return false;
    }

    private void translate(String lang) {
        TextView textViewResume = (TextView) mRootView.findViewById(R.id.summary_text_view);
        TranslateTextManager manager = new TranslateTextManager(getActivity());
        manager.setListener(this);
        manager.execute(String.valueOf(textViewResume.getText()), lang);
    }

    private void setLayoutTitle(String title) {
        mCollapsingToolbarLayout.setTitle(title);
    }

    private void applyPalette(Palette palette) {
        if (isAdded()) {
            int primaryDark = getResources().getColor(R.color.primary_dark);
            int primary = getResources().getColor(R.color.primary);
            mCollapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
            mCollapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        }
    }
}
