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
import com.example.appfilm_2.click.UpdateItemClickListener;
import com.example.appfilm_2.model.FilmModel;

import java.util.ArrayList;

public class AdapterUpdateFilm extends RecyclerView.Adapter<AdapterUpdateFilm.UpdateFilmViewHolder> {
    private ArrayList<FilmModel> mCast = new ArrayList<>();
    private UpdateItemClickListener updateItemClickListener;

    Context context;
    Activity activity;

    public void setCastModel(ArrayList<FilmModel> data) {
        mCast.clear();
        mCast.addAll(data);
        notifyDataSetChanged();
    }

    public AdapterUpdateFilm(Context context, UpdateItemClickListener updateItemClickListener) {


        this.updateItemClickListener = updateItemClickListener;
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
    public AdapterUpdateFilm.UpdateFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new AdapterUpdateFilm.UpdateFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUpdateFilm.UpdateFilmViewHolder holder, @SuppressLint("RecyclerView") int position) {
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

    public class UpdateFilmViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_photo;
        private TextView tv_name;

        public UpdateFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photo = itemView.findViewById(R.id.iv_photo);
            tv_name = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateItemClickListener.onUpdateClick(mCast.get(getAdapterPosition()), iv_photo, getAdapterPosition());
                }
            });

        }
    }
}
