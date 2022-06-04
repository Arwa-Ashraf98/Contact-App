package com.example.contactmvvmandroom.ui.updateUser;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.contactmvvmandroom.Repo.Repository;

public class UpdateUserViewModel extends ViewModel {
    MutableLiveData<String> errorLiveData;
    MutableLiveData<String> updateLiveData;
    private Repository repo;

    public UpdateUserViewModel() {
        repo = new Repository();
        this.errorLiveData = repo.errorLiveData;
        this.updateLiveData = repo.updateLiveData;

    }

    void updateUser(String fName, String lName, String phoneNumber, int id) {
        repo.updateUser(fName, lName, phoneNumber, id);
    }

}
