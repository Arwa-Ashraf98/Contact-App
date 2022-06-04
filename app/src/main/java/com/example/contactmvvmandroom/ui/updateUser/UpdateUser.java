package com.example.contactmvvmandroom.ui.updateUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactmvvmandroom.R;
import com.example.contactmvvmandroom.data.Models.ModelUser;
import com.example.contactmvvmandroom.databinding.ActivityUpdateUserBinding;
import com.example.contactmvvmandroom.helper.Constant;
import com.example.contactmvvmandroom.ui.home.main.MainActivity;

public class UpdateUser extends AppCompatActivity {
    private ActivityUpdateUserBinding binding;
    private UpdateUserViewModel updateUserViewModel;
    private int Uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        updateUserViewModel = new ViewModelProvider(this).get(UpdateUserViewModel.class);
        String fName = getIntent().getStringExtra(Constant.FIRST_NAME);
        String lName = getIntent().getStringExtra(Constant.LAST_NAME);
        String phone = getIntent().getStringExtra(Constant.PHONE_NUMBER);
        int id = getIntent().getIntExtra(Constant.ID, 0);
        this.Uid = id;
        showData(fName, lName, phone);
        onClicks();


    }

    private void showData(String fName, String lName, String number) {
        binding.editAddFirstName.setText(fName);
        binding.editAddLastName.setText(lName);
        binding.editAddNumber.setText(number);

    }

    private void onClicks() {
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUpdatedData();

            }
        });
    }


    private void getUpdatedData() {
        String fName = binding.editAddFirstName.getText().toString().trim();
        String lName = binding.editAddLastName.getText().toString().trim();
        String number = binding.editAddNumber.getText().toString().trim();
        validation(fName, lName, number);
    }

    private void validation(String fName, String lName, String number) {
        if (fName.isEmpty()) {
            binding.editAddFirstName.setError(getString(R.string.required));
        } else if (lName.isEmpty()) {
            binding.editAddLastName.setError(getString(R.string.required));
        } else if (number.isEmpty()) {
            binding.editAddNumber.setError(getString(R.string.required));
        } else {
            updatePerson(fName, lName, number);
        }
    }


    private void updatePerson(String fName, String lName, String number) {
        ModelUser modelUser = new ModelUser();
        modelUser.setFirstName(fName);
        modelUser.setLastName(lName);
        modelUser.setNumber(number);
        updateUserViewModel.updateUser(modelUser.getFirstName(), modelUser.getLastName(), modelUser.getNumber(), Uid);
        updateUserViewModel.updateLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(UpdateUser.this, s, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UpdateUser.this, MainActivity.class));
                finish();

            }
        });
        updateUserViewModel.errorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(UpdateUser.this, s, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateUser.this, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}