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
import com.aps.biometricauthapp.databinding.FragmentSignUpAccessLevelBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpAccessLevelFragment extends Fragment {
    
    private FragmentSignUpAccessLevelBinding binding;
    private SignUpAccessLevelFragmentArgs args;

    public SignUpAccessLevelFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAccessLevelBinding.inflate(inflater, container, false);
        args = SignUpAccessLevelFragmentArgs.fromBundle(getArguments());
        setView();
        return binding.getRoot();
    }

    public void setView() {
        setRadioGroupOnCheckChangeListener();
        setContinueButtonClickListener();
    }

    public void setRadioGroupOnCheckChangeListener() {
        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == binding.levelOneRadioButton.getId() || checkedId == binding.levelTwoRadioButton.getId()) {
                binding.textInputPasswordLayout.setVisibility(View.VISIBLE);
                binding.textInputPassword.setText("");
            } else {
                binding.textInputPasswordLayout.setVisibility(View.GONE);
            }
        });
    }

    public void setContinueButtonClickListener() {
        binding.continueButton.setOnClickListener(v -> {
            if (isTextInputOnError()) {
                setErrorOnTextInput();
            } else {
                setErrorOnTextInput();
                Navigation.findNavController(v).navigate(R.id.action_signUpAccessLevelFragment_to_signUpPasswordFragment);
            }
        });
    }


    private boolean isTextInputOnError() {
        boolean onError = false;
        if (binding.levelOneRadioButton.isChecked() && !Objects.requireNonNull(binding.textInputPassword.getText()).toString().equals("123")) {
            onError = true;
        }
        if (binding.levelTwoRadioButton.isChecked() && !Objects.requireNonNull(binding.textInputPassword.getText()).toString().equals("456")) {
            onError = true;
        }
        if (binding.levelZeroRadioButton.isChecked())
            onError = false;
        return onError;
    }


    private void setErrorOnTextInput() {
        TextInputLayout textInputLayout = binding.textInputPasswordLayout;
        if (TextUtils.isEmpty(Objects.requireNonNull(textInputLayout.getEditText()).getText())) {
            textInputLayout.setError("Campo obrigatório!");
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        if (binding.levelZeroRadioButton.isChecked())
            textInputLayout.setErrorEnabled(false);
        if (binding.levelOneRadioButton.isChecked() && !Objects.requireNonNull(binding.textInputPassword.getText()).toString().equals("123") && !TextUtils.isEmpty(binding.textInputPassword.getText())) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Senha inválida!");
        }
        if (binding.levelTwoRadioButton.isChecked() && !Objects.requireNonNull(binding.textInputPassword.getText()).toString().equals("456") && !TextUtils.isEmpty(binding.textInputPassword.getText())) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Senha inválida!");
        }
    }
}