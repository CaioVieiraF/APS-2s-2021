package com.aps.biometricauthapp.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.databinding.FragmentInfoBinding;

public class InfoFragment extends Fragment {
    private FragmentInfoBinding binding;
    public InfoFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}