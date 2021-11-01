package com.project.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.moviedb.R;
import com.project.moviedb.helper.Const;
import com.project.moviedb.model.NowPlaying;
import com.project.moviedb.model.UpComing;

import java.util.List;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.CardViewHolder> {

    private Context context;
    private List<UpComing.Results> listUpComing;
    private List<UpComing.Results> getListUpComing() {
        return listUpComing;
    }

    public void setListUpComing(List<UpComing.Results> listUpComing) {
        this.listUpComing = listUpComing;
    }

    public UpComingAdapter(Context context){
        this.context = context;
    }




    @NonNull
    @Override
    public UpComingAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_up_coming, parent, false);
        return new UpComingAdapter.CardViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UpComingAdapter.CardViewHolder holder, int position) {
        final UpComing.Results results = getListUpComing().get(position);

        String vote_average = String.valueOf(results.getVote_average());

        holder.lbl_title.setText(results.getTitle());
        holder.lbl_release_date.setText(results.getRelease_date());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_avg_rate_upcoming_card.setText(vote_average);

        Glide.with(context)
                .load(Const.IMG_URL + results.getPoster_path())
                .into(holder.img_poster);
    }

    @Override
    public int getItemCount() {
        return getListUpComing().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        TextView lbl_title, lbl_overview, lbl_release_date, lbl_avg_rate_upcoming_card;
        CardView cardView;
        ImageView img_poster;

        public CardViewHolder(View itemView) {
            super(itemView);

            lbl_title = itemView.findViewById(R.id.lbl_title_card_upcoming);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_card_upcoming);
            lbl_release_date = itemView.findViewById(R.id.lbl_release_card_upcoming);
            cardView = itemView.findViewById(R.id.cv_up_coming);
            img_poster = itemView.findViewById(R.id.img_poster_card_upcoming);
            lbl_avg_rate_upcoming_card = itemView.findViewById(R.id.lbl_avg_rate_upcoming_card);

        }
    }
}
