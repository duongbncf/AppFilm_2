package com.example.appfilm_2.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.example.appfilm_2.click.DeleteItemClickListener;
import com.example.appfilm_2.model.FilmModel;

import java.util.ArrayList;

public class AdapterDeleteFilm extends RecyclerView.Adapter<com.example.appfilm_2.adapter.AdapterDeleteFilm.DeleteFilmViewHolder> {
    private ArrayList<FilmModel> mCast = new ArrayList<>();
    private DeleteItemClickListener deleteItemClickListener;

    Context context;
    Activity activity;

    public void setCastModel(ArrayList<FilmModel> data) {
        mCast.clear();
        mCast.addAll(data);
        notifyDataSetChanged();
    }

    public AdapterDeleteFilm(Context context, DeleteItemClickListener deleteItemClickListener) {


        this.deleteItemClickListener = deleteItemClickListener;
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
    public com.example.appfilm_2.adapter.AdapterDeleteFilm.DeleteFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new com.example.appfilm_2.adapter.AdapterDeleteFilm.DeleteFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.appfilm_2.adapter.AdapterDeleteFilm.DeleteFilmViewHolder holder, @SuppressLint("RecyclerView") int position) {
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

    public class DeleteFilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_photo;
        private TextView tv_name;

        public DeleteFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            tv_name = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItemClickListener.onDeleteClick(mCast.get(getAdapterPosition()), iv_photo, getAdapterPosition());
                }
            });

        }
    }
}
