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

    public class ScifiAdapter extends RecyclerView.Adapter<com.example.appfilm_2.adapter.ScifiAdapter.ScifiViewHolder> {
        private ArrayList<FilmModel> mData = new ArrayList<>();
        private MovieItemClickListener movieItemClickListener;
        Context context;
        public void setDataModels(ArrayList<FilmModel> filmModels) {
            mData.clear();
            mData.addAll(filmModels);
            notifyDataSetChanged();
        }

        public ScifiAdapter( Context context,MovieItemClickListener movieItemClickListener) {
            this.context = context;
            this.movieItemClickListener = movieItemClickListener;

        }

//    public void setData(List<DataModel> categoryList) {
//        dataModels.addAll(categoryList);
//        notifyDataSetChanged();
//
//    }

        public interface OnclickItem {
            void onClickItemSucces(FilmModel mCategory);
        }

        @NonNull
        @Override
        public com.example.appfilm_2.adapter.ScifiAdapter.ScifiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
            return new com.example.appfilm_2.adapter.ScifiAdapter.ScifiViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ScifiAdapter.ScifiViewHolder holder, int position) {
            FilmModel filmModel = mData.get(position);
            holder.tv_name.setText(filmModel.getName());
            Glide.with(holder.iv_photo).load(mData.get(position).getAvatar()).into(holder.iv_photo);
            Glide.with(holder.iv_photo).load(mData.get(position).getAvatar())
                    .placeholder(R.drawable.place_holder)
                    .fitCenter()
                    .into((holder.iv_photo));
        }

        @Override
        public int getItemCount() {
            if (mData != null) {
                return mData.size();
            }
            return 0;
        }

        public class ScifiViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv_photo;
            private TextView tv_name;

            public ScifiViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_photo = itemView.findViewById(R.id.iv_photo);
                tv_name = itemView.findViewById(R.id.tv_name);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()), iv_photo);
                    }
                });

            }
        }
    }
