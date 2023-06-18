package com.example.chatting.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chatting.Models.StatusModel;
import com.example.chatting.R;
import com.example.chatting.databinding.FragmentStatusBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class StatusFragment extends Fragment {



    public StatusFragment() {
        // Required empty public constructor
    }

    FragmentStatusBinding binding;

    ArrayList<StatusModel> list = new ArrayList<>();

    FirebaseDatabase database;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStatusBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();


        binding.addStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Add", Toast.LENGTH_SHORT).show();
            }
        });




        return inflater.inflate(R.layout.fragment_status, container, false);
    }

}