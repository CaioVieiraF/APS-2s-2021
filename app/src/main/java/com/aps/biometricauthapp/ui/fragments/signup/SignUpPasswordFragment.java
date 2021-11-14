package com.aps.biometricauthapp.ui.fragments.signup;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentSignUpPasswordBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpPasswordFragment extends Fragment {

    private FragmentSignUpPasswordBinding binding;
    private SignUpPasswordFragmentArgs args;

    public SignUpPasswordFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpPasswordBinding.inflate(inflater, container, false);
        args = SignUpPasswordFragmentArgs.fromBundle(getArguments());
        setView();
        return binding.getRoot();
    }

    public void setView() {
        setFinishButtonClickListener();
    }

    public void setFinishButtonClickListener() {
        binding.finishButton.setOnClickListener(v -> {
            if (isTextInputOnError()) {
                setErrorOnTextInput();
            } else {
                setErrorOnTextInput();
                Navigation.findNavController(v).navigate(R.id.action_signUpPasswordFragment_to_signUpSuccessFragment);
            }
        });
    }

    private TextInputLayout[] textInputLayoutList() {
        return new TextInputLayout[]{
                binding.textInputPasswordLayout,
                binding.textInputPasswordConfirmationLayout
        };
    }

    private boolean isTextInputOnError() {
        boolean onError = false;
        TextInputLayout[] textInputLayoutList = textInputLayoutList();
        for (TextInputLayout textInputLayout : textInputLayoutList) {
            if (TextUtils.isEmpty(Objects.requireNonNull(textInputLayout.getEditText()).getText())) {
                onError = true;
            }
            if (!Objects.requireNonNull(binding.textInputPasswordConfirmation.getText()).toString().equals(binding.textInputPassword.getText().toString())) {
                onError = true;
            }
        }
        return onError;
    }

    private void setErrorOnTextInput() {
        TextInputLayout[] textInputLayoutList = textInputLayoutList();
        for (TextInputLayout textInputLayout : textInputLayoutList) {
            if (TextUtils.isEmpty(Objects.requireNonNull(textInputLayout.getEditText()).getText())) {
                textInputLayout.setError("Campo obrigatório!");
            } else {
                textInputLayout.setErrorEnabled(false);
            }
            if (!Objects.requireNonNull(binding.textInputPasswordConfirmation.getText()).toString().equals(binding.textInputPassword.getText().toString()) && !TextUtils.isEmpty(binding.textInputPasswordConfirmation.getText())) {
                binding.textInputPasswordConfirmationLayout.setErrorEnabled(true);
                binding.textInputPasswordConfirmationLayout.setError("Senhas não correspondem!");
            }
        }
    }
}