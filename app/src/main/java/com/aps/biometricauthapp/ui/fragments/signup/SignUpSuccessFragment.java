package com.aps.biometricauthapp.ui.fragments.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentSignUpSuccessBinding;

public class SignUpSuccessFragment extends Fragment {
    
    private SignUpSuccessFragmentArgs args;
    private FragmentSignUpSuccessBinding binding;

    public SignUpSuccessFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpSuccessBinding.inflate(inflater, container, false);
        args = SignUpSuccessFragmentArgs.fromBundle(getArguments());
        binding.loginButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_signUpSuccessFragment_to_loginFragment);
        });
        return binding.getRoot();
    }
}