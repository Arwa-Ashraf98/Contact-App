package com.example.contactmvvmandroom.ui.InsertUser;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contactmvvmandroom.Repo.Repository;
import com.example.contactmvvmandroom.data.Models.ModelUser;

public class InsertPersonViewModel extends ViewModel {
    Repository repository;
    MutableLiveData<String> errorLiveData;
    MutableLiveData<String> successLiveData;

    public InsertPersonViewModel() {
        repository = new Repository();
        this.successLiveData = repository.successLiveData;
        this.errorLiveData = repository.errorLiveData;


    }

    void insertUser(ModelUser modelUser) {
        repository.insertUser(modelUser);
    }


}
