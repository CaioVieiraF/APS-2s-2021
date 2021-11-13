package com.aps.biometricauthapp.ui.fragments.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.databinding.FragmentSignUpCepBinding;
import com.aps.biometricauthapp.ui.viewmodel.UserViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class SignUpCepFragment extends Fragment {

    private FragmentSignUpCepBinding binding;
    private SignUpCepFragmentArgs args;
    private UserViewModel viewModel;

    public SignUpCepFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpCepBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        args = SignUpCepFragmentArgs.fromBundle(getArguments());
        binding.continueButton.setOnClickListener(v -> {
            viewModel.getAddress(binding.textInputCep.getUnMasked()).enqueue(new Callback<Address>() {
                @Override
                public void onResponse(Call<Address> call, Response<Address> response) {
                    try {
                        if (response.body().getError()) {
                            binding.textInputCepLayout.setErrorEnabled(true);
                            binding.textInputCepLayout.setError("CEP inválido!");
                        }
                    } catch (NullPointerException e) {
                        binding.textInputCepLayout.setErrorEnabled(false);
                        Bundle bundle = new Bundle();
                        bundle.putString("cep", Objects.requireNonNull(binding.textInputCep.getUnMasked()));
                        Navigation.findNavController(v).navigate(R.id.action_signUpCepFragment_to_signUpAddressFragment, bundle);
                    }
                }

                @Override
                public void onFailure(Call<Address> call, Throwable t) {

                }
            });
        });
        return binding.getRoot();
    }
}