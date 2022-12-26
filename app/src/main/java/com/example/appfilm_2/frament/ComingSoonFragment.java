package com.example.appfilm_2.frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfilm_2.R;
import com.example.appfilm_2.adapter.AdapterAction;
import com.example.appfilm_2.adapter.AdapterCommingSoon;
import com.example.appfilm_2.click.CommingItemClickListener;
import com.example.appfilm_2.model.Comming;
import com.example.appfilm_2.model.FilmModel;
import com.example.appfilm_2.model.Trailer;
import com.example.appfilm_2.ui.FilmDetailActivity;
import com.example.appfilm_2.ui.MovieDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComingSoonFragment extends Fragment  {
    private RecyclerView rcv;
    private AdapterCommingSoon adapterCommingSoon;
    private DatabaseReference myRef;
    private ArrayList<Comming> commingArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coming_soon,container,false);
        rcv = view.findViewById(R.id.rv_coming_soon);
        commingArrayList = new ArrayList<>();
        adapterCommingSoon = new AdapterCommingSoon(requireContext(), new CommingItemClickListener() {
            @Override
            public void onMovieClick(Comming comming, ImageView movieImageView) {
                Intent intent = new Intent(requireContext(), FilmDetailActivity.class);
                intent.putExtra("video", comming.getIvlinkShare());
                startActivity(intent);
            }
        });
        rcv.setAdapter(adapterCommingSoon);
        rcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        rcv.setHasFixedSize(true);
        myRef = FirebaseDatabase.getInstance().getReference("Comming");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commingArrayList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Comming comming = data.getValue(Comming.class);
                    commingArrayList.add(comming);
                }
                adapterCommingSoon.setDataModel(commingArrayList);
                adapterCommingSoon.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        return view;
    }


}
