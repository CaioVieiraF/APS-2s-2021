package com.aps.biometricauthapp.ui.fragments.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentSignUpCepBinding;

import java.util.Objects;

public class SignUpCepFragment extends Fragment {

    private FragmentSignUpCepBinding binding;

    public SignUpCepFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpCepBinding.inflate(inflater, container, false);
        binding.continueButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("cep", Objects.requireNonNull(binding.textInputCep.getText()).toString());
            Navigation.findNavController(v).navigate(R.id.action_signUpCepFragment_to_signUpAddressFragment, bundle);
        });
        return binding.getRoot();
    }
}