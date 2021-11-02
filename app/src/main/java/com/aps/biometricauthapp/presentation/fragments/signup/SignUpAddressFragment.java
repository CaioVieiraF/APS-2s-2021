package com.aps.biometricauthapp.presentation.fragments.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentSignUpAddressBinding;

public class SignUpAddressFragment extends Fragment {

    private FragmentSignUpAddressBinding binding;

    public SignUpAddressFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAddressBinding.inflate(inflater, container, false);
        binding.continueButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_signUpAddressFragment_to_signUpAccessLevelFragment));
        return binding.getRoot();
    }
}