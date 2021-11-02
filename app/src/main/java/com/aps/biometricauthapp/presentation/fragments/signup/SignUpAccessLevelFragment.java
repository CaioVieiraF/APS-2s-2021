package com.aps.biometricauthapp.presentation.fragments.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentSignUpAccessLevelBinding;

public class SignUpAccessLevelFragment extends Fragment {
    private FragmentSignUpAccessLevelBinding binding;
    public SignUpAccessLevelFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAccessLevelBinding.inflate(inflater, container, false);
        binding.continueButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_signUpAccessLevelFragment_to_signUpPasswordFragment));
        return binding.getRoot();
    }
}