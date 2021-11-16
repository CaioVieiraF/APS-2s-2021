package com.aps.biometricauthapp.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aps.biometricauthapp.data.model.Post;
import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.databinding.FragmentInfoBinding;
import com.aps.biometricauthapp.ui.adapter.PostListAdapter;
import com.aps.biometricauthapp.ui.viewmodel.UserViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class InfoFragment extends Fragment {

    private FragmentInfoBinding binding;
    private UserViewModel viewModel;
    private List<Post> postList;
    private User user;

    public InfoFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        user = (User) requireActivity().getIntent().getSerializableExtra("user");
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getPostList().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postList = response.body();
                PostListAdapter adapter = new PostListAdapter(postList, user);
                binding.infoRecyclerView.setAdapter(adapter);
                binding.infoRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
        return binding.getRoot();
    }
}