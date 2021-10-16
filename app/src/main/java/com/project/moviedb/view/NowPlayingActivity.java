package com.project.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.project.moviedb.R;
import com.project.moviedb.adapter.NowPlayingAdapter;
import com.project.moviedb.model.NowPlaying;
import com.project.moviedb.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class NowPlayingActivity extends AppCompatActivity {

    private RecyclerView rv_now_playing;
    private MovieViewModel view_model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        rv_now_playing = findViewById(R.id.rv_now_playing);
        view_model = new ViewModelProvider(NowPlayingActivity.this).get(MovieViewModel.class);
        view_model.getNowPlaying();
        view_model.getResultNowPlaying();
        view_model.getResultNowPlaying().observe(NowPlayingActivity.this, showNowPlaying);
    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_now_playing.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
            NowPlayingAdapter adapter = new NowPlayingAdapter(NowPlayingActivity.this);
            adapter.setListNowPlaying((ArrayList<NowPlaying.Results>) nowPlaying.getResults());
            rv_now_playing.setAdapter(adapter);
        }
    };
}