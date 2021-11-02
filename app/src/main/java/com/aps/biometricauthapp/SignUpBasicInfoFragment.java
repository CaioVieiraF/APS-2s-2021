package com.aps.biometricauthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.databinding.FragmentSignUpBasicInfoBinding;

public class SignUpBasicInfoFragment extends Fragment {

    private FragmentSignUpBasicInfoBinding binding;

    public SignUpBasicInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBasicInfoBinding.inflate(inflater, container, false);
        binding.continueButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_signUpBasicInfoFragment_to_signUpAddressFragment));
        return binding.getRoot();
    }
}