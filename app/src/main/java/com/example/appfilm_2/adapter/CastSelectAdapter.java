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
import com.example.appfilm_2.model.Author;
import com.example.appfilm_2.click.CastItemClickListener;
import com.example.appfilm_2.ui.InsertFilmActivity;

import java.util.ArrayList;

public class CastSelectAdapter extends RecyclerView.Adapter<CastSelectAdapter.CastViewHolder> {
    private ArrayList<Author> mCast = new ArrayList<>();
    private CastItemClickListener castItemClickListener;

    Context context;
    Activity activity;

    public void setCastModel(ArrayList<Author> data) {
        mCast.clear();
        mCast.addAll(data);
        notifyDataSetChanged();
    }

    public CastSelectAdapter(Activity activity, Context context) {
        this.context = context;
        this.activity = activity;
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
    public CastSelectAdapter.CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new CastSelectAdapter.CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastSelectAdapter.CastViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Author author = mCast.get(position);
        holder.tv_name.setText(author.getName());
        Glide.with(holder.iv_photo).load(mCast.get(position).getAvatar()).into(holder.iv_photo);
        Glide.with(holder.iv_photo).load(mCast.get(position).getAvatar())
                .placeholder(R.drawable.place_holder)
                .fitCenter()
                .into((holder.iv_photo));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFilmActivity.idCast = author.getIdAuthor();
                InsertFilmActivity.nameCast = author.getName();
                activity.finish();
            }
        });
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }
    }
}
