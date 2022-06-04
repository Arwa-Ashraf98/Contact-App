package com.example.contactmvvmandroom.ui.showContact;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.contactmvvmandroom.databinding.ActivityShowContactBinding;
import com.example.contactmvvmandroom.helper.Constant;
import com.example.contactmvvmandroom.ui.updateUser.UpdateUser;

public class ShowContact extends AppCompatActivity {
    private ActivityShowContactBinding binding;
    String fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String fName = getIntent().getStringExtra(Constant.FIRST_NAME);
        String lName = getIntent().getStringExtra(Constant.LAST_NAME);
        String phone = getIntent().getStringExtra(Constant.PHONE_NUMBER);
        int id = getIntent().getIntExtra(Constant.ID, 0);
        fullName = fName + " " + lName;
        showData(fullName, phone);
        onClicks(fName, lName, phone, id);


    }

    private void showData(String fullName, String number) {
        binding.textFullName.setText(fullName);
        binding.textPhoneNumber.setText(number);

    }

    private void onClicks(String fName, String lName, String number, int id) {
        binding.btnUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(ShowContact.this, UpdateUser.class);
            intent.putExtra(Constant.FIRST_NAME, fName);
            intent.putExtra(Constant.LAST_NAME, lName);
            intent.putExtra(Constant.PHONE_NUMBER, number);
            intent.putExtra(Constant.ID, id);
            startActivity(intent);
        });
        binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });
        binding.btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}