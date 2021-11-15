package com.aps.biometricauthapp.ui.fragments.login;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.databinding.FragmentLoginBinding;
import com.aps.biometricauthapp.ui.activities.HomeActivity;
import com.aps.biometricauthapp.ui.viewmodel.UserViewModel;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private UserViewModel viewModel;
    private FragmentLoginBinding binding;
    private SharedPreferences sharedPreferences;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        sharedPreferences = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        binding.rememberCredentialsSwitch.setChecked(sharedPreferences.getBoolean("switch", binding.rememberCredentialsSwitch.isChecked()));
        clearUser();
        setUserAndPassword();
        binding.rememberCredentialsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> sharedPreferences.edit().putBoolean("switch", isChecked).apply());
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        automaticAutheticate();
        binding.loginButton.setOnClickListener(v -> validateUser());
        binding.registerButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_signUpBasicInfoFragment));
        return binding.getRoot();
    }

    private void setUserAndPassword() {
        binding.textInputEmail.setText(sharedPreferences.getString("email", null));
        binding.textInputPassword.setText(sharedPreferences.getString("password", null));
    }

    private void saveUser() {
        if (binding.rememberCredentialsSwitch.isChecked()) {
            sharedPreferences.edit().putString("email", binding.textInputEmail.getText().toString()).apply();
            sharedPreferences.edit().putString("password", binding.textInputPassword.getText().toString()).apply();
        }
    }

    private void clearUser() {
        if (!binding.rememberCredentialsSwitch.isChecked()) {
            sharedPreferences.edit().remove("email").apply();
            sharedPreferences.edit().remove("password").apply();
        }
    }

    private void validateUser() {
        setErrorOnTextInput();
        viewModel.getGetAllUsers().observe(getViewLifecycleOwner(), users -> {
            ifUsersIsEmpty(users);
            for (User user : users) {
                if (user.getEmail().equals(binding.textInputEmail.getText().toString()))
                    binding.textInputEmailLayout.setErrorEnabled(false);
                if (user.getEmail().equals(binding.textInputEmail.getText().toString()) && user.getPassword().equals(binding.textInputPassword.getText().toString())) {
                    Log.d("nic", user.toString());
                    ifUserAndPasswordIsCorrect(user);
                } else {
                    ifUserOrPasswordIsIncorrect(user);
                }
            }
        });
    }

    private void automaticAutheticate() {
        String email = sharedPreferences.getString("email", null);
        String password = sharedPreferences.getString("password", null);
        viewModel.getGetAllUsers().observe(getViewLifecycleOwner(), users ->{
            for (User user : users) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password) && user.getBiometricEnabled()) {
                    authenticate(user);
                }
            }
        });
    }


    private void ifUsersIsEmpty(List<User> users) {
        if (users.isEmpty()) {
            binding.textInputEmailLayout.setErrorEnabled(true);
            binding.textInputPasswordLayout.setErrorEnabled(true);
            binding.textInputEmailLayout.setError("Nenhum usuário cadastrado!");
            binding.textInputPasswordLayout.setError("Nenhum usuário cadastrado!");
        }
    }

    private void ifUserAndPasswordIsCorrect(User user) {
        binding.textInputPasswordLayout.setErrorEnabled(false);
        saveUser();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        ActivityUtils.startActivity(bundle, HomeActivity.class);
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void ifUserOrPasswordIsIncorrect(User user) {
        binding.textInputEmailLayout.setErrorEnabled(true);
        binding.textInputPasswordLayout.setErrorEnabled(true);
        if (!user.getEmail().equals(binding.textInputEmail.getText().toString()) && user.getPassword().equals(binding.textInputPassword.getText().toString())) {
            binding.textInputPasswordLayout.setErrorEnabled(true);
        }
        if (!user.getEmail().equals(binding.textInputEmail.getText().toString()) && RegexUtils.isEmail(binding.textInputEmail.getText()))
            binding.textInputEmailLayout.setError("E-mail incorreto!");
        if (!user.getPassword().equals(binding.textInputPassword.getText().toString()) && !TextUtils.isEmpty(binding.textInputPassword.getText().toString()))
            binding.textInputPasswordLayout.setError("Senha incorreta!");
        if (!user.getEmail().equals(binding.textInputEmail.getText().toString()) && user.getPassword().equals(binding.textInputPassword.getText().toString()) && !TextUtils.isEmpty(binding.textInputPassword.getText().toString())) {
            binding.textInputPasswordLayout.setError("Senha incorreta");
        }
    }

    private TextInputLayout[] textInputLayoutList() {
        return new TextInputLayout[]{
                binding.textInputEmailLayout,
                binding.textInputPasswordLayout
        };
    }

    private void setErrorOnTextInput() {
        TextInputLayout[] textInputLayoutList = textInputLayoutList();
        for (TextInputLayout textInputLayout : textInputLayoutList) {
            if (TextUtils.isEmpty(Objects.requireNonNull(textInputLayout.getEditText()).getText())) {
                textInputLayout.setError("Campo obrigatório!");
            } else if (!RegexUtils.isEmail(binding.textInputEmail.getText()) && !TextUtils.isEmpty(binding.textInputEmail.getText().toString())) {
                binding.textInputEmailLayout.setError("Formato de e-mail inválido");
            } else {
                textInputLayout.setErrorEnabled(false);
            }
        }
    }

    public void authenticate(User user) {
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
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            ActivityUtils.startActivity(bundle, HomeActivity.class);
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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

        biometricPrompt.authenticate(promptInfo);
    }
}