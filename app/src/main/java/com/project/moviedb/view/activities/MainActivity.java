package com.project.moviedb.view.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.project.moviedb.R;
import com.project.moviedb.helper.Const;
import com.project.moviedb.model.Movies;
import com.project.moviedb.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private Button btn_hit_main;
    private TextView txt_show;
    private TextInputLayout til_movie_id;
    private ImageView img_poster_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0c2d7a"));
        actionBar.setBackgroundDrawable(colorDrawable);

        til_movie_id = findViewById(R.id.til_movie_id);
        txt_show = findViewById(R.id.txt_show_main);
        img_poster_main = findViewById(R.id.img_poster_main);

        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        btn_hit_main = findViewById(R.id.btn_hit_main);
        btn_hit_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = til_movie_id.getEditText().getText().toString().trim();

                if (movieId.isEmpty()) {
                    til_movie_id.setError("Please fill movie id field");
                } else{
                    til_movie_id.setError(null);
                    viewModel.getMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                }
//                viewModel.getMovieById("358");
//                viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
            }
        });

    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if(movies == null){
                txt_show.setText("Movie ID is not available in MovieDB");
            } else {
                String title = movies.getTitle();
                String img_path = movies.getPoster_path().toString();
//                String full_path = "";
                Glide.with(MainActivity.this).load(Const.IMG_URL + img_path).into(img_poster_main);
                txt_show.setText(title);
            }
//            String title = movies.getTitle();
//            txt_show.setText(title);

        }
    };
}