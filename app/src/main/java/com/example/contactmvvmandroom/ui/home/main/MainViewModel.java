package com.example.contactmvvmandroom.ui.home.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contactmvvmandroom.Repo.Repository;
import com.example.contactmvvmandroom.data.Models.ModelUser;

import java.util.List;

public class MainViewModel extends ViewModel {
    MutableLiveData<List<ModelUser>> showAllContactLiveData;
    MutableLiveData<String> deletedLiveData;
    MutableLiveData<String> errorLiveData;

    Repository repo;

    public MainViewModel() {
        repo = new Repository();
        this.errorLiveData = repo.errorLiveData;
        this.showAllContactLiveData = repo.showAllContactLiveData;
        this.deletedLiveData = repo.deletedLiveData;
    }

    void showAllContact() {
        repo.showAllContact();
    }

    void deleteContact(ModelUser modelUser) {
        repo.deleteUser(modelUser);
    }

}
