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
import com.example.appfilm_2.model.Trailer;
import com.example.appfilm_2.click.TrailerItemClickListener;

import java.util.ArrayList;

    public class TrailerAdapter extends RecyclerView.Adapter<com.example.appfilm_2.adapter.TrailerAdapter.TrailerViewHolder> {
        private ArrayList<Trailer> mData = new ArrayList<>();
        private TrailerItemClickListener trailerItemClickListener;
        Context context;
        public TrailerAdapter(Context context) {
            this.context = context;
        }
        public void setFilteredList(ArrayList<Trailer> filmModels){
            this.mData = filmModels;
            notifyDataSetChanged();
        }
        public void setDataModels(ArrayList<Trailer> filmModels) {
            mData.clear();
            mData.addAll(filmModels);
            notifyDataSetChanged();
        }
        //    public interface OnclickItem {
//        void onClickItemSucces(DataModel mCategory);
//    }
        @NonNull
        @Override
        public com.example.appfilm_2.adapter.TrailerAdapter.TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
            return new com.example.appfilm_2.adapter.TrailerAdapter.TrailerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.appfilm_2.adapter.TrailerAdapter.TrailerViewHolder holder, int position) {
            Trailer trailer = mData.get(position);
            holder.tv_name.setText(trailer.getTtitle());
            Glide.with(holder.iv_photo).load(mData.get(position).getTurl())
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


        public class TrailerViewHolder extends RecyclerView.ViewHolder {
            private ImageView iv_photo;
            private TextView tv_name;

            public TrailerViewHolder(@NonNull View itemView) {
                super(itemView);
                iv_photo = itemView.findViewById(R.id.iv_photo);
                tv_name = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    trailerItemClickListener.onTrailerClick(mData.get(getAdapterPosition()), iv_photo);
                }
            });

            }
        }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String strSearch = constraint.toString();
//                List<DataModel> list = new ArrayList<>();
//                for(DataModel dataModel : mData){
//                    if(dataModel.getTtitle().toLowerCase().contains(strSearch.toLowerCase())){
//                        list.add(dataModel);
//                    }
//
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = mData;
//
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                    mData = (ArrayList<DataModel>) results.values;
//                    notifyDataSetChanged();
//            }
//        };
//    }
    }

