package com.example.appfilm_2.frament;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.appfilm_2.R;
import com.example.appfilm_2.adapter.CategoryAdapter;
import com.example.appfilm_2.adapter.ScifiAdapter;
import com.example.appfilm_2.adapter.ViewPagerListAdapter;
import com.example.appfilm_2.model.FilmModel;
import com.example.appfilm_2.model.Trailer;
import com.example.appfilm_2.ui.MovieDetailActivity;
import com.example.appfilm_2.click.MovieItemClickListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sunzn.banner.library.Banner;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements MovieItemClickListener {

    private DatabaseReference myRef;
    private DatabaseReference myRef1;
    private ArrayList<FilmModel> filmModelList;
    private ArrayList<Trailer> trailerArrayList;
    private ArrayList<FilmModel> bestFilmModelList;
    private ArrayList<FilmModel> trendingFilmModelList;
    private ArrayList<FilmModel> skiFilmModelList;
    private CategoryAdapter TrendingcategoryAdapter;
    private CategoryAdapter BestcategoryAdapter;
    private ScifiAdapter scifiAdapter;
    private RecyclerView rcvBest;
    private RecyclerView rv_Sci_fi;
    private RecyclerView rcvTrending;
    private Banner<Trailer> banner;
    private TabLayout tabLayout;
    private ViewPagerListAdapter viewPagerListAdapter;
    private ViewPager mViewPager;

    void finbyid(View view) {
        rcvBest = view.findViewById(R.id.rv_photos);
        rcvTrending = view.findViewById(R.id.rv_trending);
        rv_Sci_fi = view.findViewById(R.id.rv_Sci_fi);
        skiFilmModelList = new ArrayList<>();
        filmModelList = new ArrayList<>();
        bestFilmModelList = new ArrayList<>();
        trendingFilmModelList = new ArrayList<>();
        trailerArrayList = new ArrayList<>();
        scifiAdapter = new ScifiAdapter(requireContext(),this::onMovieClick);
        TrendingcategoryAdapter = new CategoryAdapter(requireContext(), this::onMovieClick);
        BestcategoryAdapter = new CategoryAdapter(requireContext(), this::onMovieClick);
        tabLayout = view.findViewById(R.id.tablayout_list);
        mViewPager = view.findViewById(R.id.viewpager_list);
        //Banner
        banner = view.findViewById(R.id.banner);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        finbyid(view);
        initViewPager();
        initRecyclerView();
        getAllFilm();
        initBanner();
        return view;

    }

    void initViewPager() {
        viewPagerListAdapter = new ViewPagerListAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerListAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    void initRecyclerView() {
        rcvTrending.setAdapter(TrendingcategoryAdapter);
        rcvTrending.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvTrending.setHasFixedSize(true);
        rv_Sci_fi.setAdapter(scifiAdapter);
        rv_Sci_fi.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_Sci_fi.setHasFixedSize(true);
        rcvBest.setAdapter(BestcategoryAdapter);
        rcvBest.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rcvBest.setHasFixedSize(true);
    }

    void getAllFilm() {
        myRef = FirebaseDatabase.getInstance().getReference("Film");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                filmModelList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    FilmModel filmModel = data.getValue(FilmModel.class);
                    filmModelList.add(filmModel);
                    finterFilmByCategories();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void finterFilmByCategories() {
        bestFilmModelList.clear();
        trendingFilmModelList.clear();
        skiFilmModelList.clear();
        for (int i = 0; i < filmModelList.size(); i++) {
            //id 1 la best 2 la treding
            if (filmModelList.get(i).getIdCategories() == 1) {
                bestFilmModelList.add(filmModelList.get(i));
            } else if (filmModelList.get(i).getIdCategories() == 2) {
                trendingFilmModelList.add(filmModelList.get(i));
            }else if (filmModelList.get(i).getIdCategories() == 8) {
                skiFilmModelList.add(filmModelList.get(i));
            }


        }
        setBestFilm();
        setTrendingFilm();
        setScifiFilm();
    }



    private void initBanner() {
//

//        filmModelListMax3.add(filmModelList);
//        banner.setBannerData(filmModelList);
        myRef = FirebaseDatabase.getInstance().getReference("trailer");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trailerArrayList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Trailer trailer = data.getValue(Trailer.class);
                    trailerArrayList.add(trailer);
                    banner.setBannerData(trailerArrayList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
//
        banner.setOnItemClickListener(new Banner.OnItemClickListener<Trailer>() {
            @Override
            public void onItemClick(int position, Trailer item) {
                Intent intent = new Intent(requireContext(), MovieDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", item.getTtitle());
                bundle.putString("url", item.getTurl());
                bundle.putString("video", item.getTvid());
                bundle.putString("cover", item.getTcover());
                bundle.putString("depcription", item.getTdepcription());
                bundle.putString("author", item.getTauthor());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        banner.setOnItemBindListener(new Banner.OnItemBindListener<Trailer>() {
            @Override
            public void onItemBind(int position, Trailer item, ImageView view) {
                Glide.with(view).load(item.getTcover()).into(view);
            }
        });
    }


    private void setBestFilm() {
        BestcategoryAdapter.setDataModels(bestFilmModelList);
        BestcategoryAdapter.notifyDataSetChanged();
    }

    private void setTrendingFilm() {
        TrendingcategoryAdapter.setDataModels(trendingFilmModelList);
        TrendingcategoryAdapter.notifyDataSetChanged();
    }
    private void setScifiFilm() {
        scifiAdapter.setDataModels(skiFilmModelList);
        scifiAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMovieClick(FilmModel filmModel, ImageView movieImageView) {
        Intent intent = new Intent(requireContext(), MovieDetailActivity.class);
        intent.putExtra("title", filmModel.getName());
        intent.putExtra("url", filmModel.getAvatar());
        intent.putExtra("video", filmModel.getUrl());
        intent.putExtra("cover", filmModel.getCover_image());
        intent.putExtra("depcription", filmModel.getDepcription());
        intent.putExtra("author", filmModel.getIdAuthor());

//        intent.putExtra("author",dataModel.getTauthor());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(requireActivity(), movieImageView, "shareName");
        startActivity(intent, options.toBundle());
    }
}
