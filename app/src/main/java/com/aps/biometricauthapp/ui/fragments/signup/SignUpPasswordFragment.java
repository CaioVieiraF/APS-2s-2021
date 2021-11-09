package com.aps.biometricauthapp.ui.fragments.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentSignUpPasswordBinding;

public class SignUpPasswordFragment extends Fragment {
    private FragmentSignUpPasswordBinding binding;
    public SignUpPasswordFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpPasswordBinding.inflate(inflater, container, false);
        binding.finishButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_signUpPasswordFragment_to_signUpSuccessFragment);
        });
        return binding.getRoot();
    }
}