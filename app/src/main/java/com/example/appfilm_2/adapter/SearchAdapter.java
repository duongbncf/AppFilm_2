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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private ArrayList<FilmModel> mData = new ArrayList<>();
    private ArrayList<FilmModel> mDataOld = new ArrayList<>();
    private MovieItemClickListener movieItemClickListener;
    Context context;
    public SearchAdapter(Context context,MovieItemClickListener movieItemClickListener) {
        this.movieItemClickListener = movieItemClickListener;
        this.context = context;
    }
    public void setFilteredList(ArrayList<FilmModel> filmModels){
        this.mData = filmModels;
        notifyDataSetChanged();
    }
    public void setDataModels(ArrayList<FilmModel> filmModels) {
        mData.clear();
        mData.addAll(filmModels);
        notifyDataSetChanged();
    }
//    public interface OnclickItem {
//        void onClickItemSucces(DataModel mCategory);
//    }
    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        FilmModel filmModel = mData.get(position);
        holder.tvSearch.setText(filmModel.getName());
        Glide.with(holder.imageSearch).load(mData.get(position).getAvatar()).into(holder.imageSearch);
        Glide.with(holder.imageSearch).load(mData.get(position).getAvatar())
                .placeholder(R.drawable.place_holder)
                .fitCenter()
                .into((holder.imageSearch));
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageSearch;
        private TextView tvSearch;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSearch = itemView.findViewById(R.id.vImageSearch);
            tvSearch = itemView.findViewById(R.id.tvSearch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()), imageSearch);
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
