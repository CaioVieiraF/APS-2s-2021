package com.aps.biometricauthapp.ui.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.R;
import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.databinding.FragmentProfileBinding;
import com.aps.biometricauthapp.ui.viewmodel.UserViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private UserViewModel viewModel;
    private User user;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        user = (User) requireActivity().getIntent().getSerializableExtra("user");
        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        binding.userData.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_userDataFragment));
        binding.userAddress.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_userAddressFragment));
        binding.userAccessAndToken.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_userAccessLevelFragment));
        useBiometric();
        binding.useBiometricsSwitch.setChecked(user.getBiometricEnabled());
        return binding.getRoot();
    }

    private void useBiometric() {
        binding.useBiometricsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            User updatedUser = new User(
                    user.getAddress(),
                    user.getCpf(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getBirthday(),
                    user.getPassword(),
                    user.getLoginActivity(),
                    user.getAccessLevelKey(),
                    user.getAccessLevel(),
                    isChecked);
            updatedUser.setId(user.getId());
            viewModel.updateUser(updatedUser);
        });
    }
}