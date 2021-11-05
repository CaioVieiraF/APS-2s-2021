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
import com.aps.biometricauthapp.databinding.FragmentSignUpBasicInfoBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpBasicInfoFragment extends Fragment {

    private FragmentSignUpBasicInfoBinding binding;

    public SignUpBasicInfoFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBasicInfoBinding.inflate(inflater, container, false);
        binding.continueButton.setOnClickListener(v -> {
            if (isTextInputEmpty()) {
                setErrorOnTextInput();
            } else {
                Navigation.findNavController(v).navigate(R.id.action_signUpBasicInfoFragment_to_signUpAddressFragment);
            }
        });
        return binding.getRoot();
    }

    private TextInputLayout[] textInputLayoutList() {
        return new TextInputLayout[]{
                binding.textInputCpfLayout,
                binding.textInputNameLayout,
                binding.textInputBirthdayLayout,
                binding.textInputPhoneLayout,
                binding.textInputEmailLayout,
                binding.textInputEmailConfirmationLayout
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