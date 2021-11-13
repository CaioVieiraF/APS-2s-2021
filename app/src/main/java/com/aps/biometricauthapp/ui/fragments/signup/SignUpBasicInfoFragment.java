package com.aps.biometricauthapp.ui.fragments.signup;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.aps.biometricauthapp.databinding.FragmentSignUpBasicInfoBinding;
import com.aps.biometricauthapp.util.DatePickerFragment;
import com.blankj.utilcode.util.RegexUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SignUpBasicInfoFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private FragmentSignUpBasicInfoBinding binding;

    public SignUpBasicInfoFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBasicInfoBinding.inflate(inflater, container, false);
        setView();
        return binding.getRoot();
    }

    public void setView() {
        setPickerForBirthdayLayout();
        setContinueButtonClickListener();
    }

    public void setContinueButtonClickListener() {
        binding.continueButton.setOnClickListener(v -> {
            if (isTextInputOnError()) {
                setErrorOnTextInput();
            } else {
                setErrorOnTextInput();
                String name = binding.textInputName.getText().toString();
                String cpf = binding.textInputCpf.getText().toString();
                String birthday = binding.textInputBirthday.getText().toString();
                String email = binding.textInputEmail.getText().toString();
                String phone = binding.textInputPhone.getText().toString();
                SignUpBasicInfoFragmentDirections.ActionSignUpBasicInfoFragmentToSignUpCepFragment action = SignUpBasicInfoFragmentDirections.actionSignUpBasicInfoFragmentToSignUpCepFragment(name, cpf, birthday, email, phone);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    private TextInputLayout[] textInputLayoutList() {
        return new TextInputLayout[]{
                binding.textInputCpfLayout,
                binding.textInputNameLayout,
                binding.textInputBirthdayLayout,
                binding.textInputPhoneLayout,
                binding.textInputEmailLayout,
                binding.textInputEmailConfirmationLayout
        };
    }

    private boolean isTextInputOnError() {
        boolean onError = false;
        TextInputLayout[] textInputLayoutList = textInputLayoutList();
        for (TextInputLayout textInputLayout : textInputLayoutList) {
            if (TextUtils.isEmpty(Objects.requireNonNull(textInputLayout.getEditText()).getText())) {
                onError = true;
            }
            if (!RegexUtils.isEmail(binding.textInputEmail.getText()) && !TextUtils.isEmpty(binding.textInputEmail.getText())) {
                onError = true;
            }
            if (!Objects.requireNonNull(binding.textInputEmailConfirmation.getText()).toString().equals(binding.textInputEmail.getText().toString()) && !TextUtils.isEmpty(binding.textInputEmailConfirmation.getText())) {
                onError = true;
            }
            if (!RegexUtils.isTel(binding.textInputPhone.getMasked()) && !binding.textInputPhone.isDone() && !TextUtils.isEmpty(binding.textInputPhone.getText())) {
                onError = true;
            }
            // TODO: 13/11/2021 terminar essa validação de cpf
            if (!binding.textInputCpf.isDone() && !TextUtils.isEmpty(binding.textInputCpf.getText())) {
                onError = true;
            }
        }
        return onError;
    }

    private void setErrorOnTextInput() {
        // TODO: 07/11/2021 fazer validação do cpf
        TextInputLayout[] textInputLayoutList = textInputLayoutList();
        for (TextInputLayout textInputLayout : textInputLayoutList) {
            if (TextUtils.isEmpty(Objects.requireNonNull(textInputLayout.getEditText()).getText())) {
                textInputLayout.setError("Campo obrigatório!");
            } else {
                textInputLayout.setErrorEnabled(false);
            }
            if (!RegexUtils.isEmail(binding.textInputEmail.getText()) && !TextUtils.isEmpty(binding.textInputEmail.getText())) {
                binding.textInputEmailLayout.setErrorEnabled(true);
                binding.textInputEmailLayout.setError("Formato de e-mail inválido!");
            }
            if (!Objects.requireNonNull(binding.textInputEmailConfirmation.getText()).toString().equals(binding.textInputEmail.getText().toString()) && !TextUtils.isEmpty(binding.textInputEmailConfirmation.getText())) {
                binding.textInputEmailConfirmationLayout.setErrorEnabled(true);
                binding.textInputEmailConfirmationLayout.setError("E-mail não correspondente!");
            }
            if (!RegexUtils.isTel(binding.textInputPhone.getMasked()) && !binding.textInputPhone.isDone() && !TextUtils.isEmpty(binding.textInputPhone.getText())) {
                binding.textInputPhoneLayout.setErrorEnabled(true);
                binding.textInputPhoneLayout.setError("Número de telefone inválido!");
            }
            // TODO: 13/11/2021 terminar essa validação de cpf
            if (!binding.textInputCpf.isDone() && !TextUtils.isEmpty(binding.textInputCpf.getText())) {
                binding.textInputCpfLayout.setErrorEnabled(true);
                binding.textInputCpfLayout.setError("CPF inválido!");
            }
        }
    }

    public void setPickerForBirthdayLayout() {
        binding.textInputBirthdayLayout.setErrorIconOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.setTargetFragment(SignUpBasicInfoFragment.this, 0);
            assert getFragmentManager() != null;
            datePicker.show(getFragmentManager(), "date picker");
        });
        binding.textInputBirthdayLayout.setEndIconOnClickListener(v -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.setTargetFragment(SignUpBasicInfoFragment.this, 0);
            assert getFragmentManager() != null;
            datePicker.show(getFragmentManager(), "date picker");
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = formatter.format(c.getTime());
        binding.textInputBirthday.setText(currentDate);
    }
}