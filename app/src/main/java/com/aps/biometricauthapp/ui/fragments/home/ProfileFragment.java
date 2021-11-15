package com.aps.biometricauthapp.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    public ProfileFragment() {
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.userData.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_userDataFragment));
        binding.userAddress.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_userAddressFragment));
        binding.userAccessAndToken.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_userAccessLevelFragment));
        return binding.getRoot();
    }
}