package com.aps.biometricauthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.databinding.FragmentSignUpBasicInfoBinding;

public class SignUpBasicInfoFragment extends Fragment {

    private FragmentSignUpBasicInfoBinding binding;

    public SignUpBasicInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBasicInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}