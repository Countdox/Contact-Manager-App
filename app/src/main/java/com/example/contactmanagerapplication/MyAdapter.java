package com.example.contactmanagerapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerapplication.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // creating new view Holder for items in recyclerview
        ContactListItemBinding contactListItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.contact_list_item,
                parent,
                false
        );

        return new  ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Contacts currentContact = contacts.get(position);

        holder.contactListItemBinding.setContact(currentContact);

    }

    @Override
    public int getItemCount() {
        if(contacts != null){
            return contacts.size();
        }else {
            return 0;
        }
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {
       // use databinding with recyclerview directly form contact list item
        private ContactListItemBinding contactListItemBinding;
        // now creating constructor


        public ContactViewHolder(@NonNull  ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }

    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;

        notifyDataSetChanged();
    }
}
