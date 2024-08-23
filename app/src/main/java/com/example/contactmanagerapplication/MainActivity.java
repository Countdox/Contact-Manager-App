package com.example.contactmanagerapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
// data source
     private ContactDatabase contactDatabase;
     private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

     // adapter
    private MyAdapter myAdapter;

    // binding
    private ActivityMainBinding mainBinding;

    private MainActivityClickHandlers handlers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

   mainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main);
  handlers = new MainActivityClickHandlers(this);
  mainBinding.setClickHandler(handlers);

  // recyclerView
     RecyclerView recyclerView = mainBinding.recyclerview;
     recyclerView.setLayoutManager(new LinearLayoutManager(this));
     recyclerView.setHasFixedSize(true);

     MyAdapter myAdapter = new MyAdapter(contactsArrayList);

     contactDatabase = ContactDatabase.getInstance(this);
     // view model
         MyViewModel viewModel = new ViewModelProvider(this)
                 .get(MyViewModel.class);

         // inserting a new Contact (just for testing)
        Contacts c1 = new Contacts("jack","jack@gmail.com");
        viewModel.addNewContact(c1);

        // loading the data from the Database room db
        viewModel.getAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> contacts) {

                        contactsArrayList.clear();

                        for(Contacts c : contacts){
                            Log.v("TAGY",c.toString());
                            contactsArrayList.add(c);
                        }
                        myAdapter.notifyDataSetChanged();
                    }
                });


        recyclerView.setAdapter(myAdapter);





    }
}