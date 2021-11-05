package com.aps.biometricauthapp.presentation.fragments.signup;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentSignUpAddressBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpAddressFragment extends Fragment {

    private FragmentSignUpAddressBinding binding;

    public SignUpAddressFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAddressBinding.inflate(inflater, container, false);
        binding.continueButton.setOnClickListener(v -> {
            if (isTextInputEmpty()) {
                setErrorOnTextInput();
            } else {
                Navigation.findNavController(v).navigate(R.id.action_signUpAddressFragment_to_signUpAccessLevelFragment);
            }
        });
        return binding.getRoot();
    }

    private TextInputLayout[] textInputLayoutList() {
        return new TextInputLayout[]{
                binding.textInputAddressComplementLayout,
                binding.textInputLocationLayout,
                binding.textInputCepLayout,
                binding.textInputUfLayout,
                binding.textInputDistrictLayout,
                binding.textInputAddressNumberLayout,
                binding.textInputPublicPlaceLayout
        };
    }

    private boolean isTextInputEmpty() {
        boolean isEmpty = false;
        TextInputLayout[] textInputLayoutList = textInputLayoutList();
        for (TextInputLayout textInputLayout : textInputLayoutList) {
            if (TextUtils.isEmpty(Objects.requireNonNull(textInputLayout.getEditText()).getText())) {
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    private void setErrorOnTextInput() {
        TextInputLayout[] textInputLayoutList = textInputLayoutList();
        for (TextInputLayout textInputLayout : textInputLayoutList) {
            if (TextUtils.isEmpty(Objects.requireNonNull(textInputLayout.getEditText()).getText())) {
                textInputLayout.setError("Campo obrigatório!");
            } else {
                textInputLayout.setErrorEnabled(false);
            }
        }
    }
}