package com.project.moviedb.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.moviedb.R;
import com.project.moviedb.adapter.CompanyAdapter;
import com.project.moviedb.helper.Const;
import com.project.moviedb.model.Movies;
import com.project.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView lbl_movie_id, lbl_movie_description_details_fragment, lbl_movie_genre_details_fragment,
            lbl_movie_rating_details_fragment, lbl_movie_popularity_details_fragment, lbl_movie_average_vote_details_fragment,
            lbl_movie_title_details_fragment, lbl_movie_tagline_details_fragment, lbl_movie_date_details_fragment;

    private ImageView img_cover_details_fragment, img_thumb_details_fragment;
    private String movieGENRE ="";
    private MovieViewModel viewModel;
    private String movieID = "";
    private RecyclerView rv_movie_companies_details_fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        img_thumb_details_fragment = view.findViewById(R.id.img_thumb_details_fragment);
        img_cover_details_fragment = view.findViewById(R.id.img_cover_details_fragment);

        lbl_movie_title_details_fragment = view.findViewById(R.id.lbl_movie_title_details_fragment);
        lbl_movie_average_vote_details_fragment = view.findViewById(R.id.lbl_movie_average_vote_details_fragment);
        lbl_movie_description_details_fragment = view.findViewById(R.id.lbl_movie_description_details_fragment);
        lbl_movie_genre_details_fragment = view.findViewById(R.id.lbl_movie_genre_details_fragment);
        lbl_movie_popularity_details_fragment = view.findViewById(R.id.lbl_movie_popularity_details_fragment);
        lbl_movie_rating_details_fragment = view.findViewById(R.id.lbl_movie_rating_details_fragment);
        lbl_movie_tagline_details_fragment = view.findViewById(R.id.lbl_movie_tagline_details_fragment);
        rv_movie_companies_details_fragment = view.findViewById(R.id.rv_movie_companies_details_fragment);
        lbl_movie_date_details_fragment = view.findViewById(R.id.lbl_movie_date_details_fragment);



        String movieID = getArguments().getString("movieID");
        viewModel = new ViewModelProvider(MovieDetailsFragment.this).get(MovieViewModel.class);
        viewModel.getMovieById(movieID);
        viewModel.getResultGetMovieById().observe(getActivity(), showResultMovie);

//        lbl_movie_id = view.findViewById(R.id.lbl_movie_id_details_fragment);

//        String movieID = getArguments().getString("movieID");
//        lbl_movie_id.setText(movieID);

        return view;
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String img_path = Const.IMG_URL+ movies.getPoster_path().toString();
            String img_path_back = Const.IMG_URL+ movies.getBackdrop_path();
            String vote_average = String.valueOf(movies.getVote_average());
            String popularity = String.valueOf(movies.getPopularity());
            String voting = String.valueOf(movies.getVote_count());



            rv_movie_companies_details_fragment.setLayoutManager(new GridLayoutManager(getActivity(),
                    1, RecyclerView.HORIZONTAL, false));

            CompanyAdapter adapter = new CompanyAdapter(getActivity());
            adapter.setCompaniesList(movies.getProduction_companies());
            rv_movie_companies_details_fragment.setAdapter(adapter);

            Glide.with(MovieDetailsFragment.this).load(img_path).into(img_thumb_details_fragment);
            Glide.with(MovieDetailsFragment.this).load(img_path_back).into(img_cover_details_fragment);

            lbl_movie_title_details_fragment.setText(movies.getTitle());
            lbl_movie_description_details_fragment.setText(movies.getOverview());
            lbl_movie_tagline_details_fragment.setText(movies.getTagline());
            lbl_movie_average_vote_details_fragment.setText(vote_average);
            lbl_movie_popularity_details_fragment.setText(popularity);
            lbl_movie_rating_details_fragment.setText(voting);
            lbl_movie_date_details_fragment.setText(movies.getRelease_date());


            for (int i=0; i<movies.getGenres().size(); i++){

                if(i==movies.getGenres().size()-1){
                    movieGENRE+=movies.getGenres().get(i).getName();
                } else {
                    movieGENRE+=movies.getGenres().get(i).getName()+ ", ";
                }

            }

            lbl_movie_genre_details_fragment.setText(movieGENRE);
            lbl_movie_rating_details_fragment.setText(" " + voting);
        }
    };
}