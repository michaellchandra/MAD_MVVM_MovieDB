package com.project.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Movie;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.moviedb.R;
import com.project.moviedb.helper.Const;
import com.project.moviedb.model.Movies;
import com.project.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView details_textView_rating, details_textView_title,details_textView_genre, details_textView_description;
    private ImageView details_imageView;
    private MovieViewModel viewModel;
    private String movieID ="";
    private String movieGENRE ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent intent = getIntent();
        movieID = intent.getStringExtra("movieID");
        viewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        details_textView_genre = findViewById(R.id.details_textView_genre);
        details_imageView = findViewById(R.id.details_imageView);
        details_textView_rating = findViewById(R.id.details_textView_rating);
        details_textView_title = findViewById(R.id.details_textView_title);
        details_textView_description = findViewById(R.id.details_textView_description);
        viewModel.getMovieById(movieID);
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovie);

    }

    private Observer<Movies>showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String img_path = movies.getPoster_path().toString();

            Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL + img_path).into(details_imageView);
            details_textView_title.setText(movies.getTitle());

            details_textView_description.setText(movies.getOverview());


        }
    };
}