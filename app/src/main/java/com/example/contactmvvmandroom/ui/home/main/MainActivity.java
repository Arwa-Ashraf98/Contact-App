package com.example.contactmvvmandroom.ui.home.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactmvvmandroom.Adapter.AdapterContact;
import com.example.contactmvvmandroom.data.Models.ModelUser;
import com.example.contactmvvmandroom.databinding.ActivityMainBinding;
import com.example.contactmvvmandroom.helper.Constant;
import com.example.contactmvvmandroom.ui.InsertUser.InsertPerson;
import com.example.contactmvvmandroom.ui.showContact.ShowContact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    private final AdapterContact adapter = new AdapterContact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        showALLContact();
        onClicks();
    }

    private void onClicks() {
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InsertPerson.class);
                startActivity(intent);
            }
        });
        adapter.setOnContactListener(new AdapterContact.setOnContactListener() {
            @Override
            public void onItemClick(String fName, String lName, String number, int id) {
                Intent intent = new Intent(MainActivity.this, ShowContact.class);
                intent.putExtra(Constant.FIRST_NAME, fName);
                intent.putExtra(Constant.LAST_NAME, lName);
                intent.putExtra(Constant.PHONE_NUMBER, number);
                intent.putExtra(Constant.ID, id);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(ModelUser modelUser) {
                mainViewModel.deleteContact(modelUser);
                mainViewModel.errorLiveData.observe(MainActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
                mainViewModel.deletedLiveData.observe(MainActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        showALLContact();
    }

    private void showALLContact() {
        mainViewModel.showAllContact();
        mainViewModel.showAllContactLiveData.observe(this, new Observer<List<ModelUser>>() {

            @Override
            public void onChanged(List<ModelUser> list) {
                adapter.setList((ArrayList<ModelUser>) list);
                binding.contactRecycler.setAdapter(adapter);
            }
        });

        mainViewModel.errorLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}