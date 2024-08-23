package com.example.contactmanagerapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    // now the repository is a database we need to inatialise the repository
    private Repository myrepository;

    private LiveData<List<Contacts>> allContacts;

    // now we need to inatialise the repository

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myrepository = new Repository(application);
    }
    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = myrepository.getAllContacts();
        return allContacts;
    }

    public void addNewContact(Contacts contact){
        myrepository.addContact(contact);
    }
    public void deleteContact(Contacts contact){
        myrepository.deleteContact(contact);

    }
}
