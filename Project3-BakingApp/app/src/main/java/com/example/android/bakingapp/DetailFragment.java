package com.example.android.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailFragment extends Fragment implements ExoPlayer.EventListener {
    @BindView(R.id.videoView)
    SimpleExoPlayerView playerView;
    @BindView(R.id.previous_step_button)
    Button previousStep;
    @BindView(R.id.next_step_button)
    Button nextStep;
    @BindView(R.id.stepDescription)
    TextView stepTextView;
    String description;
    String videoURL;
    private ArrayList<Step> steps;
    private int position;
    private SimpleExoPlayer exoPlayer;
    private PlaybackStateCompat.Builder stateBuilder;
    private MediaSessionCompat mediaSession;
    public DetailFragment() {}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this, v);
        Bundle b = getArguments();
        description = b.getString("description");
        videoURL = b.getString("videoURL");
        steps = b.getParcelableArrayList("steps");
        position = b.getInt("position");
        if(videoURL.length() <= 1) {
            Snackbar snackbar = Snackbar
                    .make(v, "No video available.", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
        stepTextView.setText(description);
        if (position == steps.size() - 1) {
            nextStep.setVisibility(View.GONE);
        }
        if (position == 0) {
            previousStep.setVisibility(View.INVISIBLE);
        }
        previousStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                Step s = steps.get(position);
                Intent i = new Intent(getContext(), DetailActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("nextStep", s);
                b.putInt("position", position);
                b.putString("description", s.getDescription());
                b.putString("videoURL", s.getVideoUrl());
                i.putExtras(b);
                startActivity(i);
            }
        });
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                Step s = steps.get(position);
                Intent i = new Intent(getContext(), DetailActivity.class);
                Bundle b = new Bundle();
                b.putParcelable("nextStep", s);
                b.putInt("position", position);
                b.putString("description", s.getDescription());
                b.putString("videoURL", s.getVideoUrl());
                DetailFragment fragment = new DetailFragment();
                fragment.setArguments(b);
                startActivity(i);
            }
        });
        mediaSession = new MediaSessionCompat(getContext(), DetailFragment.class.getSimpleName());
        mediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mediaSession.setMediaButtonReceiver(null);
        stateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);
        mediaSession.setPlaybackState(stateBuilder.build());
        mediaSession.setCallback(new MySessionCallback());
        mediaSession.setActive(true);
        SimpleExoPlayerView playerView = new SimpleExoPlayerView(getActivity().getApplicationContext());
        exoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector());
        exoPlayer.addListener(this);
        String agent = Util.getUserAgent(getContext(), getString(R.string.app_name));
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoURL), new DefaultDataSourceFactory(getContext(), agent), new DefaultExtractorsFactory(), null, null);
        exoPlayer.prepare(mediaSource);
        playerView.setPlayer(exoPlayer);
        return v;
    }
    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }
    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }
    @Override
    public void onLoadingChanged(boolean isLoading) {

    }
    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if((playbackState == ExoPlayer.STATE_READY) && playWhenReady){
            stateBuilder.setState(PlaybackStateCompat.STATE_PLAYING,
                    exoPlayer.getCurrentPosition(), 1f);
        } else if((playbackState == ExoPlayer.STATE_READY)){
            stateBuilder.setState(PlaybackStateCompat.STATE_PAUSED,
                    exoPlayer.getCurrentPosition(), 1f);
        }
        mediaSession.setPlaybackState(stateBuilder.build());
    }
    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }
    @Override
    public void onPositionDiscontinuity() {

    }
    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }
    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            exoPlayer.setPlayWhenReady(true);
        }
        @Override
        public void onPause() {
            exoPlayer.setPlayWhenReady(false);
        }
        @Override
        public void onSkipToPrevious() {
            exoPlayer.seekTo(0);
        }
    }
}
