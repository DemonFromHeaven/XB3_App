package com.example.apptest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        EditText searchBar = findViewById(R.id.search_bar);
        ArrayList<String> restaurantNames = new ArrayList<>();
        restaurantNames.add("Restaurant 1");
        restaurantNames.add("Restaurant 2");
        ListView searchResults = findViewById(R.id.search_results);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.result_list_layout, restaurantNames);
        searchResults.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
