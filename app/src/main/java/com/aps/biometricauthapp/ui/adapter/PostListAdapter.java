package com.aps.biometricauthapp.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.aps.biometricauthapp.data.model.Post;
import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.databinding.ItemInfoBinding;
import com.aps.biometricauthapp.util.AccessLevel;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private List<Post> postList;
    private User user;

    public PostListAdapter(List<Post> postList, User user) {
        this.postList = postList;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardViewTextView.setText(postList.get(position).body);
        if (position % 2 == 0 && user.getAccessLevel() == AccessLevel.DIVISION_DIRECTOR || user.getAccessLevel() == AccessLevel.ENVIRONMENT_MINISTER) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#fffe52"));
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#d3d3d3"));
        }
        if (position % 3 == 0 && user.getAccessLevel() == AccessLevel.ENVIRONMENT_MINISTER) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#54d97f"));
        } else if (position % 2 != 0){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#d3d3d3"));
        }
        if (user.getAccessLevel() == AccessLevel.PUBLIC) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#d3d3d3"));
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cardViewTextView;
        private CardView cardView;

        public ViewHolder(ItemInfoBinding binding) {
            super(binding.getRoot());
            cardViewTextView = binding.cardViewTextView;
            cardView = binding.cardView;
        }
    }
}
