package com.aps.biometricauthapp.ui.fragments.signup;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.databinding.FragmentSignUpAddressBinding;
import com.aps.biometricauthapp.ui.viewmodel.UserViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AndroidEntryPoint
public class SignUpAddressFragment extends Fragment {

    private FragmentSignUpAddressBinding binding;
    private SignUpAddressFragmentArgs args;
    private UserViewModel viewModel;

    public SignUpAddressFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpAddressBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        args = SignUpAddressFragmentArgs.fromBundle(getArguments());
        viewModel.getAddress(args.getCepRaw()).enqueue(new Callback<Address>() {
            @Override
            public void onResponse(Call<Address> call, Response<Address> response) {
                Address address = response.body();
                binding.textInputPublicPlace.setText(address.getPublicPlace());
                binding.textInputDistrict.setText(address.getDistrict());
                binding.textInputUf.setText(address.getUf());
                binding.textInputLocation.setText(address.getLocation());
            }

            @Override
            public void onFailure(Call<Address> call, Throwable t) {

            }
        });
        binding.continueButton.setOnClickListener(v -> {
            if (isTextInputEmpty()) {
                setErrorOnTextInput();
            } else {
                SignUpAddressFragmentDirections.ActionSignUpAddressFragmentToSignUpAccessLevelFragment action =
                        SignUpAddressFragmentDirections.actionSignUpAddressFragmentToSignUpAccessLevelFragment(
                                args.getName(),
                                args.getCpf(),
                                args.getBirthday(),
                                args.getEmail(),
                                args.getPhone(),
                                args.getCepMasked(),
                                binding.textInputPublicPlace.getText().toString(),
                                binding.textInputAddressNumber.getText().toString() + ", " + binding.textInputAddressComplement.getText().toString(),
                                binding.textInputDistrict.getText().toString(),
                                binding.textInputLocation.getText().toString(),
                                binding.textInputUf.getText().toString()
                                );
                Navigation.findNavController(v).navigate(action);
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
                textInputLayout.setError("Campo obrigat??rio!");
            } else {
                textInputLayout.setErrorEnabled(false);
            }
        }
    }
}