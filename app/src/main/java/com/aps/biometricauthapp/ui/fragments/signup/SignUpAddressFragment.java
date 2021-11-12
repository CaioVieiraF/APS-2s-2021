package com.aps.biometricauthapp.ui.fragments.signup;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.databinding.FragmentSignUpAddressBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class SignUpAddressFragment extends Fragment {

    private FragmentSignUpAddressBinding binding;
    private SignUpViewModel viewModel;

    public SignUpAddressFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAddressBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        Bundle bundle = new Bundle();
        Log.d("nicolas", getArguments().getString("cep"));
        viewModel.getAddress(getArguments().getString("cep")).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                Address address = response.body();
                binding.textInputPublicPlace.setText(address.getPublicPlace());
//                binding.textInputAddressNumber.setText();
                binding.textInputDistrict.setText(address.getDistrict());
                binding.textInputUf.setText(address.getUf());
                binding.textInputLocation.setText(address.getLocation());
//                binding.textInputAddressComplement.setText(address.getComplement());
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
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