package com.example.appfilm_2.frament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfilm_2.R;
import com.example.appfilm_2.adapter.AdapterAction;
import com.example.appfilm_2.model.FilmModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentDrama extends Fragment {
    private RecyclerView rcv;
    private AdapterAction adapterAction;
    private DatabaseReference myRef1;
    private ArrayList<FilmModel> filmModelList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_action, container, false);
        rcv = view.findViewById(R.id.rv_photos);
        filmModelList = new ArrayList<>();
        adapterAction = new AdapterAction(requireContext());
        rcv.setAdapter(adapterAction);
        rcv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rcv.setHasFixedSize(true);
        setBestFilm();
        return view;
    }
    private void setBestFilm() {
        myRef1 = FirebaseDatabase.getInstance().getReference("Film");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    FilmModel filmModel = data.getValue(FilmModel.class);
                    filmModelList.add(filmModel);
                }
                adapterAction.setCastModel(filmModelList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
