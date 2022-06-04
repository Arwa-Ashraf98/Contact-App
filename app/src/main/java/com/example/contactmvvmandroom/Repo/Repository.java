package com.example.contactmvvmandroom.Repo;

import androidx.lifecycle.MutableLiveData;

import com.example.contactmvvmandroom.data.Models.ModelUser;
import com.example.contactmvvmandroom.data.local.MyRoomDatabase;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public MutableLiveData<String> successLiveData = new MutableLiveData<>();
    public MutableLiveData<String> updateLiveData = new MutableLiveData<>();
    public MutableLiveData<String> deletedLiveData = new MutableLiveData<>();
    public MutableLiveData<List<ModelUser>> showAllContactLiveData = new MutableLiveData<>();
    private MyRoomDatabase myRoomDatabase = MyRoomDatabase.getInstance();

    public void showAllContact() {

        myRoomDatabase.getDao()
                .showAllContact()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ModelUser>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<ModelUser> list) {
                        showAllContactLiveData.setValue(list);

                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(e.getLocalizedMessage());

                    }
                });
    }


    public void updateUser(String fName, String lName, String phoneNumber, int id) {
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                MyRoomDatabase.getInstance().getDao()
//                        .updateUser(modelUser);
//                return null;
//            }
//        }.execute();
        myRoomDatabase.getDao()
                .updateUser(fName, lName, phoneNumber, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        updateLiveData.setValue("Updated");
                        showAllContact();

                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(e.getLocalizedMessage());

                    }
                });
    }


    public void insertUser(ModelUser modelUser) {
        myRoomDatabase
                .getDao()
                .insertUser(modelUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        successLiveData.setValue("success");
                        showAllContact();


                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(e.getLocalizedMessage());

                    }
                });

    }

    public void deleteUser(ModelUser modelUser) {
        myRoomDatabase
                .getDao()
                .deleteUser(modelUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        deletedLiveData.setValue("Deleted");
                        showAllContact();


                    }

                    @Override
                    public void onError(Throwable e) {
                        errorLiveData.setValue(e.getLocalizedMessage());

                    }
                });
    }


}
