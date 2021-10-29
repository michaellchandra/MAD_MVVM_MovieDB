package com.project.moviedb.view.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.moviedb.R;
import com.project.moviedb.helper.Const;
import com.project.moviedb.model.Movies;
import com.project.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView details_textView_rating, details_textView_title,details_textView_genre, details_textView_description;
    private ImageView details_imageView, details_imageView_back;
    private MovieViewModel viewModel;
    private String movieID ="";
    private String movieGENRE ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1C59F4"));
//        actionBar.setBackgroundDrawable(colorDrawable);

        Intent intent = getIntent();
        movieID = intent.getStringExtra("movieID");

        viewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        details_textView_genre = findViewById(R.id.details_textView_genre);
        details_imageView = findViewById(R.id.details_imageView);
        details_imageView_back = findViewById(R.id.details_imageView_back);
        details_textView_rating = findViewById(R.id.details_textView_rating);
        details_textView_title = findViewById(R.id.details_textView_title);
        details_textView_description = findViewById(R.id.details_textView_description);
        viewModel.getMovieById(movieID);
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovie);

        details_imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NowPlayingActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private Observer<Movies>showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String img_path = movies.getPoster_path().toString();

            Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL + img_path).into(details_imageView);

            details_textView_title.setText(movies.getTitle());

            details_textView_description.setText(movies.getOverview());

            for (int i=0; i<movies.getGenres().size(); i++){

                if(i==movies.getGenres().size()-1){
                    movieGENRE+=movies.getGenres().get(i).getName();
                } else {
                    movieGENRE+=movies.getGenres().get(i).getName()+ ", ";
                }

            }

            details_textView_genre.setText(movieGENRE);
            details_textView_rating.setText(" " + movies.getVote_average());


        }
    };
}