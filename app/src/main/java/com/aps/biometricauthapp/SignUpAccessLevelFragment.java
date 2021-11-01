package com.aps.biometricauthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.databinding.FragmentSignUpAccessLevelBinding;

public class SignUpAccessLevelFragment extends Fragment {
    private FragmentSignUpAccessLevelBinding binding;
    public SignUpAccessLevelFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAccessLevelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}