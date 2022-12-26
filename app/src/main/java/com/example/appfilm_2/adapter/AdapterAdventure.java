package com.example.appfilm_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfilm_2.R;
import com.example.appfilm_2.model.FilmModel;
import com.example.appfilm_2.click.MovieItemClickListener;

import java.util.ArrayList;

public class AdapterAdventure extends RecyclerView.Adapter<AdapterAdventure.AdventureViewHolder> {
    private ArrayList<FilmModel> mCast = new ArrayList<>();
    private MovieItemClickListener movieItemClickListener;
    Context context;
    public void setDataModel(ArrayList<FilmModel> data) {
        mCast.clear();
        mCast.addAll(data);
        notifyDataSetChanged();
    }

    public AdapterAdventure( Context context) {
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
    public AdapterAdventure.AdventureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new AdapterAdventure.AdventureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAdventure.AdventureViewHolder holder, int position) {
        FilmModel filmModel = mCast.get(position);
        holder.tv_name.setText(filmModel.getName());
        Glide.with(holder.iv_photo).load(mCast.get(position).getAvatar()).into(holder.iv_photo);
        Glide.with(holder.iv_photo).load(mCast.get(position).getAvatar())
                .placeholder(R.drawable.place_holder)
                .fitCenter()
                .into((holder.iv_photo));
    }

    @Override
    public int getItemCount() {
        if (mCast != null) {
            return mCast.size();
        }
        return 0;
    }

    public class AdventureViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_photo;
        private TextView tv_name;

        public AdventureViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            tv_name = itemView.findViewById(R.id.tv_name);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    movieItemClickListener.onMovieClick(mCast.get(getAdapterPosition()), iv_photo);
//                }
//            });

        }
    }
}

