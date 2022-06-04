package com.example.contactmvvmandroom.ui.InsertUser;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactmvvmandroom.R;
import com.example.contactmvvmandroom.data.Models.ModelUser;
import com.example.contactmvvmandroom.databinding.ActivityInsertUserBinding;

public class InsertPerson extends AppCompatActivity {
    private ActivityInsertUserBinding binding;
    private InsertPersonViewModel insertPersonViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        insertPersonViewModel = new ViewModelProvider(this).get(InsertPersonViewModel.class);
        onClicks();


    }

    private void onClicks() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserData();
                clear();

            }
        });

    }

    private void getUserData() {
        String firstName = binding.editAddFirstName.getText().toString().trim();
        String lastName = binding.editAddLastName.getText().toString().trim();
        String number = binding.editAddNumber.getText().toString().trim();
        validation(firstName, lastName, number);

    }

    private void validation(String fName, String lName, String number) {
        if (fName.isEmpty()) {
            binding.editAddFirstName.setError(getString(R.string.required));
        } else if (lName.isEmpty()) {
            binding.editAddLastName.setError(getString(R.string.required));
        } else if (number.isEmpty()) {
            binding.editAddNumber.setError(getString(R.string.required));
        } else {
            insertUser(fName, lName, number);
        }
    }

    private void insertUser(String fName, String lName, String phoneNumber) {
        ModelUser modelUser = new ModelUser();
        modelUser.setFirstName(fName);
        modelUser.setLastName(lName);
        modelUser.setNumber(phoneNumber);
        insertPersonViewModel.insertUser(modelUser);
        insertPersonViewModel.errorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(InsertPerson.this, s, Toast.LENGTH_SHORT).show();
            }
        });
        insertPersonViewModel.successLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(InsertPerson.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void clear() {
        binding.editAddFirstName.setText("");
        binding.editAddLastName.setText("");
        binding.editAddNumber.setText("");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
//    private void setData(String fName, String lName, String phoneNumber , ModelUser modelUser){
//        modelUser = new ModelUser();
//        modelUser.setFirstName(fName);
//        modelUser.setLastName(lName);
//        modelUser.setNumber(phoneNumber);
//    }