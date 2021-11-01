package com.aps.biometricauthapp;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.databinding.FragmentLoginBinding;
import com.blankj.utilcode.util.RegexUtils;

import java.util.Calendar;
import java.util.concurrent.Executor;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        executor = ContextCompat.getMainExecutor(getActivity());
        biometricPrompt = new BiometricPrompt(getActivity(), executor, new BiometricPrompt.AuthenticationCallback() {
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
                .build();
        // TODO: 01/11/2021 Verificar como adicionar a validação biometrica automaticamente ao entrar no app 
//        biometricPrompt.authenticate(promptInfo);
        binding.textInputEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (RegexUtils.isEmail(s)) {
                    binding.textInputEmailLayout.setError(null);
                } else {
                    binding.textInputEmailLayout.setError("Formato de e-mail inválido");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.loginButton.setOnClickListener(v -> biometricPrompt.authenticate(promptInfo));
        return binding.getRoot();
    }
}