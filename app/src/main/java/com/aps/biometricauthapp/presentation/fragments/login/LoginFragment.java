package com.aps.biometricauthapp.presentation.fragments.login;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.HomeActivity;
import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentLoginBinding;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.Executor;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        executor = ContextCompat.getMainExecutor(requireActivity());
        biometricPrompt = new BiometricPrompt(requireActivity(), executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getActivity(), errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getActivity(), Calendar.getInstance().getTime().toLocaleString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getActivity(), "Falhou", Toast.LENGTH_SHORT).show();
            }
        });
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Ministério do Meio Ambiente")
                .setSubtitle("Desbloqueie seu app")
                .setAllowedAuthenticators(BIOMETRIC_STRONG | BIOMETRIC_WEAK | DEVICE_CREDENTIAL)
                .setConfirmationRequired(true)
                .build();
        // TODO: 01/11/2021 Verificar como adicionar a validação biometrica automaticamente ao entrar no app 
//        biometricPrompt.authenticate(promptInfo);
//        binding.textInputEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (RegexUtils.isEmail(s)) {
//                    binding.textInputEmailLayout.setErrorEnabled(false);
//                } else {
//                    binding.textInputEmailLayout.setError("Formato de e-mail inválido");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//        binding.loginButton.setOnClickListener(v -> {
//            if (isTextInputEmpty()) {
//
//            }
//        });
        binding.loginButton.setOnClickListener(v -> {
            ActivityUtils.startActivity(HomeActivity.class);
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        binding.registerButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_signUpBasicInfoFragment));
//        biometricPrompt.authenticate(promptInfo);
        return binding.getRoot();
    }

    private TextInputLayout[] textInputLayoutList() {
        return new TextInputLayout[]{
                binding.textInputEmailLayout,
                binding.textInputPasswordLayout
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
            } else if (!RegexUtils.isEmail(binding.textInputEmail.getText())) {
                binding.textInputEmailLayout.setError("Formato de e-mail inválido");
            } else {
                textInputLayout.setErrorEnabled(false);
            }
        }
    }
}