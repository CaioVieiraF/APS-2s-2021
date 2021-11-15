package com.aps.biometricauthapp.ui.fragments.home.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aps.biometricauthapp.data.model.User;
import com.aps.biometricauthapp.databinding.FragmentUserAccessLevelBinding;
import com.aps.biometricauthapp.util.AccessLevel;

public class UserAccessLevelFragment extends Fragment {

    private FragmentUserAccessLevelBinding binding;

    public UserAccessLevelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserAccessLevelBinding.inflate(inflater, container, false);
        User user = (User) requireActivity().getIntent().getSerializableExtra("user");
        if (user.getAccessLevel().equals(AccessLevel.PUBLIC)) {
            binding.userAccessLevel.setText("Nível de acesso: Público");
            binding.userToken.setText("Token: Token não necessário para o acesso público");
        } else if (user.getAccessLevel().equals(AccessLevel.DIVISION_DIRECTOR)) {
            binding.userAccessLevel.setText("Nível de acesso: Diretor de Divisão");
            binding.userToken.setText("Token: 123");
        } else if (user.getAccessLevel().equals(AccessLevel.ENVIRONMENT_MINISTER)) {
            binding.userAccessLevel.setText("Nível de acesso: Ministro do Meio Ambiente");
            binding.userToken.setText("Token: 456");
        }
        return binding.getRoot();
    }
}