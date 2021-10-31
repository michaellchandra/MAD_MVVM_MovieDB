package com.project.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.moviedb.R;
import com.project.moviedb.helper.Const;
import com.project.moviedb.model.Movies;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CardViewViewHolder> {

    private Context context;
    private List<Movies.ProductionCompanies> companiesList;
    public CompanyAdapter(Context context) {
        this.context = context;
    }

    public List<Movies.ProductionCompanies> getCompaniesList() {
        return companiesList;
    }


    public void setCompaniesList (List<Movies.ProductionCompanies> companiesList){
        this.companiesList = companiesList;
    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public CompanyAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_companies_details, parent, false);


        return new CardViewViewHolder(view);
    }




    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.CardViewViewHolder holder, int position) {
        final Movies.ProductionCompanies results = getCompaniesList().get(position);

        Glide.with(context)
                .load(Const.IMG_URL + results.getLogo_path())
                .into(holder.img_card_companies_details);

        holder.img_card_companies_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, results.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getCompaniesList().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        ImageView img_card_companies_details;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            img_card_companies_details = itemView.findViewById(R.id.img_card_companies_details);
        }
    }
}
