package com.example.apptest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Default Activity after Log In (Home)
 */
public class HomeActivity extends AppCompatActivity{

    private RestaurantAdapter adapter;

    private void launchSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_filter:
                    return true;
                case R.id.navigation_settings:
                    launchSettings();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Restaurant[] rArray = new Restaurant[5];

        //Example Sample
        for (int i = 0; i < rArray.length; i++) {
            rArray[i] = new Restaurant("Restaurant " + i, "ID " + i,
                    new Location("Address " + i,
                            "City " + i,
                            "State " + i,
                            -10.0*(i+1),
                            10.0*(i+1)), 4.0*(i+1)%5, 5*(i+1)%6, 100*(i+1));
        }

        EditText searchBar = findViewById(R.id.search_bar);
        ArrayList<Restaurant> restaurantNames = new ArrayList<>();

        for (Restaurant r:
             rArray) {
            restaurantNames.add(r);
        }

        ListView searchResults = findViewById(R.id.search_results);

        adapter = new RestaurantAdapter(this, R.layout.adapter_list_layout, restaurantNames);
        searchResults.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (HomeActivity.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        private Context mContext;
        private int mResource;
        private int lastPosition = -1;

        class ViewHolder {
            TextView name;
            TextView address;
            TextView rating;
        }

        RestaurantAdapter(Context context, int resource, ArrayList<Restaurant> textViewResourceId) {
            super(context, resource, textViewResourceId);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String name = getItem(position).getName();
            String address = getItem(position).getLocation().getAddress();
            String rating = Double.toString(getItem(position).getStars());

            final View result;

            ViewHolder holder;

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(mResource, parent, false);

                holder = new ViewHolder();
                holder.name = convertView.findViewById(R.id.rest_name);
                holder.address = convertView.findViewById(R.id.rest_address);
                holder.rating = convertView.findViewById(R.id.rest_rating);

                result = convertView;
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
                result = convertView;
            }


            Animation animation = AnimationUtils.loadAnimation(mContext,
                    (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);

            result.startAnimation(animation);
            lastPosition = position;

            holder.name.setText(name);
            holder.address.setText(address);
            holder.rating.setText(rating);

            return convertView;
        }
    }
}
