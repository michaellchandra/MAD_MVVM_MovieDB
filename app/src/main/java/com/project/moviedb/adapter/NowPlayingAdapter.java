package com.project.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.moviedb.R;
import com.project.moviedb.helper.Const;
import com.project.moviedb.model.NowPlaying;
import com.project.moviedb.view.activities.MovieDetailsActivity;

import java.util.ArrayList;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<NowPlaying.Results> listNowPlaying;
    private ArrayList<NowPlaying.Results> getListNowPlaying () {
        return listNowPlaying;
    }
    public void setListNowPlaying(ArrayList<NowPlaying.Results> listNowPlaying) {
        this.listNowPlaying = listNowPlaying;
    }

    public NowPlayingAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
        return new NowPlayingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final NowPlaying.Results results = getListNowPlaying().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_release_date.setText(results.getRelease_date());
        holder.lbl_overview.setText(results.getOverview());

        Glide.with(context)
                .load(Const.IMG_URL + results.getPoster_path())
                .into(holder.img_poster);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movieID","" + results.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title, lbl_overview, lbl_release_date;
        CardView cardView;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.img_poster_card_nowplaying);
            lbl_title = itemView.findViewById(R.id.lbl_title_card_nowplaying);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_card_nowplaying);
            lbl_release_date = itemView.findViewById(R.id.lbl_release_card_nowplaying);
            cardView = itemView.findViewById(R.id.cv_card_nowplaying);
        }
    }
}
