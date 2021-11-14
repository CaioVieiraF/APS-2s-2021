package com.aps.biometricauthapp.ui.fragments.signup;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.databinding.FragmentSignUpAccessLevelBinding;
import com.aps.biometricauthapp.util.AccessLevel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignUpAccessLevelFragment extends Fragment {
    
    private FragmentSignUpAccessLevelBinding binding;
    private SignUpAccessLevelFragmentArgs args;
    private AccessLevel accessLevel;

    public SignUpAccessLevelFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAccessLevelBinding.inflate(inflater, container, false);
        accessLevel = AccessLevel.PUBLIC;
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
                if (checkedId == binding.levelOneRadioButton.getId()) accessLevel = AccessLevel.DIVISION_DIRECTOR;
                if (checkedId == binding.levelTwoRadioButton.getId()) accessLevel = AccessLevel.ENVIRONMENT_MINISTER;
            } else {
                accessLevel = AccessLevel.PUBLIC;
                binding.textInputPassword.setText("");
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
                SignUpAccessLevelFragmentDirections.ActionSignUpAccessLevelFragmentToSignUpPasswordFragment action =
                        SignUpAccessLevelFragmentDirections.actionSignUpAccessLevelFragmentToSignUpPasswordFragment(
                                args.getName(),
                                args.getCpf(),
                                args.getBirthday(),
                                args.getEmail(),
                                args.getPhone(),
                                args.getCepMasked(),
                                args.getPublicPlace(),
                                args.getAddressNumberAndComplement(),
                                args.getDistrict(),
                                args.getLocation(),
                                args.getUf(),
                                accessLevel,
                                binding.textInputPassword.getText().toString()
                        );
                Navigation.findNavController(v).navigate(action);
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