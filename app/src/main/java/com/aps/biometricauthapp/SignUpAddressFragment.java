package com.aps.biometricauthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.databinding.FragmentSignUpAddressBinding;

public class SignUpAddressFragment extends Fragment {

    private FragmentSignUpAddressBinding binding;

    public SignUpAddressFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAddressBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}