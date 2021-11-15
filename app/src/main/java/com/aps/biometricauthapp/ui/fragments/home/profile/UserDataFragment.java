package com.aps.biometricauthapp.ui.fragments.home.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.databinding.FragmentUserDataBinding;

public class UserDataFragment extends Fragment {

    private FragmentUserDataBinding binding;

    public UserDataFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserDataBinding.inflate(inflater, container, false);
        User user = (User) requireActivity().getIntent().getSerializableExtra("user");
        binding.userName.setText("Nome: " + user.getName());
        binding.userCpf.setText("CPF: " + user.getCpf());
        binding.userBirthday.setText("Data de Nascimento: " + user.getBirthday());
        binding.userEmail.setText("E-mail: " + user.getEmail());
        binding.userPhone.setText("Celular: " + user.getPhone());
        return binding.getRoot();
    }
}