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
import com.example.appfilm_2.model.Author;
import com.example.appfilm_2.click.CastItemClickListener;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private ArrayList<Author> mCast = new ArrayList<>();
    private CastItemClickListener castItemClickListener;

    Context context;
    public void setCastModel(ArrayList<Author> data) {
        mCast.clear();
        mCast.addAll(data);
        notifyDataSetChanged();
    }

    public CastAdapter( Context context) {
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
    public CastAdapter.CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new CastAdapter.CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.CastViewHolder holder, int position) {
        Author author = mCast.get(position);
        holder.tv_name.setText(author.getName());
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

    public class CastViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_photo;
        private TextView tv_name;

        public CastViewHolder(@NonNull View itemView) {
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
