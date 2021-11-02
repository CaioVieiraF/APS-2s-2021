package com.aps.biometricauthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.databinding.FragmentSignUpPasswordBinding;

public class SignUpPasswordFragment extends Fragment {
    private FragmentSignUpPasswordBinding binding;
    public SignUpPasswordFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}