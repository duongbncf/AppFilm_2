package com.example.appfilm_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfilm_2.R;
import com.example.appfilm_2.click.CommingItemClickListener;
import com.example.appfilm_2.click.MovieItemClickListener;
import com.example.appfilm_2.model.Comming;
import com.example.appfilm_2.model.FilmModel;
import com.example.appfilm_2.ui.FilmDetailActivity;
import com.example.appfilm_2.ui.MovieDetailActivity;

import java.util.ArrayList;


public class AdapterCommingSoon extends RecyclerView.Adapter<com.example.appfilm_2.adapter.AdapterCommingSoon.CommingSoonViewHolder> {
    private ArrayList<Comming> mComming = new ArrayList<>();



    private CommingItemClickListener commingItemClickListener;
    Context context;

    public void setDataModel(ArrayList<Comming> data) {
        mComming.clear();
        mComming.addAll(data);
        notifyDataSetChanged();
    }

    public AdapterCommingSoon( Context context,CommingItemClickListener commingItemClickListener) {
        this.commingItemClickListener = commingItemClickListener;
        this.context = context;
    }

    //    public void setData(List<DataModel> categoryList) {
//        dataModels.addAll(categoryList);
//        notifyDataSetChanged();
//
//    }

//    public interface OnclickItem {
//        void onClickItemSucces(DataModel mCategory);
//    }

    @NonNull
    @Override
    public com.example.appfilm_2.adapter.AdapterCommingSoon.CommingSoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comming_soon, parent, false);
        return new com.example.appfilm_2.adapter.AdapterCommingSoon.CommingSoonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCommingSoon.CommingSoonViewHolder holder, int position) {
        Comming comming = mComming.get(position);
        holder.TV_name_film1.setText(comming.getName());
        holder.TV_comingtime_film1.setText(comming.getCommingTime());
        holder.TV_description_film1.setText(comming.getDescription());
        Glide.with(holder.IV_coming_soon).load(mComming.get(position).getIvlink()).into(holder.IV_coming_soon);
    }

    @Override
    public int getItemCount() {
        if (mComming != null) {
            return mComming.size();
        }
        return 0;
    }

    public class CommingSoonViewHolder extends RecyclerView.ViewHolder {
        private ImageView IV_coming_soon;
        private ImageView IV_share_film1;
        private TextView TV_comingtime_film1;
        private TextView TV_name_film1;
        private TextView TV_description_film1;

        public CommingSoonViewHolder(@NonNull View itemView) {
            super(itemView);
            IV_coming_soon = itemView.findViewById(R.id.IV_coming_soon);
            TV_comingtime_film1 = itemView.findViewById(R.id.TV_comingtime_film1);
            TV_name_film1 = itemView.findViewById(R.id.TV_name_film1);
            TV_description_film1 = itemView.findViewById(R.id.TV_description_film1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    commingItemClickListener.onMovieClick(mComming.get(getAdapterPosition()), IV_coming_soon);
                }
            });

        }
    }
}
