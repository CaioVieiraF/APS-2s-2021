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
import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.databinding.FragmentSignUpSuccessBinding;
import com.aps.biometricauthapp.ui.viewmodel.UserViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpSuccessFragment extends Fragment {

    private UserViewModel viewModel;
    private SignUpSuccessFragmentArgs args;
    private FragmentSignUpSuccessBinding binding;

    public SignUpSuccessFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpSuccessBinding.inflate(inflater, container, false);
        args = SignUpSuccessFragmentArgs.fromBundle(getArguments());
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        insertUser();
        binding.loginButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_signUpSuccessFragment_to_loginFragment));
        return binding.getRoot();
    }

    public void insertUser() {
        Address address = getAddress();
        viewModel.insertUser(new User(
                address,
                args.getCpf(),
                args.getName(),
                args.getEmail(),
                args.getPhone(),
                args.getBirthday(),
                args.getPassword(),
                new ArrayList<String>(),
                args.getAccessLevelKey(),
                args.getAccessLevel(),
                args.getIsBiometricEnabled())
        );
    }

    public Address getAddress() {
        return new Address(
                args.getCepMasked(),
                args.getPublicPlace(),
                args.getAddressNumberAndComplement(),
                args.getDistrict(),
                args.getLocation(),
                args.getUf()
        );
    }
}