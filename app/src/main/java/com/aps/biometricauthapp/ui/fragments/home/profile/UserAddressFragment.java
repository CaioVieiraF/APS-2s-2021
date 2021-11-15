package com.aps.biometricauthapp.ui.fragments.home.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.data.model.Address;
import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.databinding.FragmentUserAddressBinding;

public class UserAddressFragment extends Fragment {

    private FragmentUserAddressBinding binding;

    public UserAddressFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserAddressBinding.inflate(inflater, container, false);
        User user = (User) requireActivity().getIntent().getSerializableExtra("user");
        Address address = user.getAddress();
        binding.userCep.setText("CEP: " + address.getCep());
        binding.userPublicPlace.setText("Endereço: " + address.getPublicPlace());
        binding.userNumberAndComplement.setText("Número e Complemento: " + address.getComplement());
        binding.userDistrict.setText("Bairro: " + address.getDistrict());
        binding.userLocation.setText("Cidade: " + address.getLocation());
        binding.userUf.setText("Estado: " + address.getUf());
        return binding.getRoot();
    }
}