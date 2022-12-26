package com.example.appfilm_2.frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfilm_2.R;
import com.example.appfilm_2.adapter.SearchAdapter;
import com.example.appfilm_2.click.MovieItemClickListener;
import com.example.appfilm_2.model.FilmModel;
import com.example.appfilm_2.ui.MovieDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements MovieItemClickListener {
    private EditText edtSearch;
    DatabaseReference myRef;
    ArrayList<FilmModel> filmModelList;
    SearchAdapter searchAdapter;
    RecyclerView rcvSearch;
    SearchView searchView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        rcvSearch = view.findViewById(R.id.rcvSearch);

        searchAdapter = new SearchAdapter(requireContext(),this::onMovieClick);
        filmModelList = new ArrayList<>();
        rcvSearch.setAdapter(searchAdapter);
        rcvSearch.setHasFixedSize(true);
        searchView = view.findViewById(R.id.searchView);
        searchView.clearFocus();
        rcvSearch.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        myRef = FirebaseDatabase.getInstance().getReference("Film");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    FilmModel filmModel = data.getValue(FilmModel.class);
                    filmModelList.add(filmModel);

                }
                searchAdapter.setDataModels(filmModelList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
//        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
//        appCompatActivity.getSupportActionBar().setTitle("Search");

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filerList(newText);
                return false;
            }
                    private void filerList(String text) {
                        ArrayList<FilmModel> filteredList = new ArrayList<>();
                        for(FilmModel filmModel : filmModelList){
                            if(filmModel.getName().toLowerCase().contains(text.toLowerCase())){
                                filteredList.add(filmModel);
                            }
                        }
                        if(filteredList.isEmpty()){
//                            Toast.makeText(requireContext(),"No data found",Toast.LENGTH_LONG).show();
                        }else {
                            searchAdapter.setFilteredList(filteredList);
                        }
                    }
                });

        return view;
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
        startActivity(intent);
    }
}
