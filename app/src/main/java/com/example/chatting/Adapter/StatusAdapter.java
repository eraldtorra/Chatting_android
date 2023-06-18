package com.example.chatting.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatting.Models.StatusModel;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    ArrayList<StatusModel> list;

    Context context;


    public StatusAdapter(ArrayList<StatusModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
