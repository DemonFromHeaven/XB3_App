package com.example.apptest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        Restaurant r1 = new Restaurant("Restaurant 1", "Address 1", "4.0");
        Restaurant r2 = new Restaurant("Restaurant 2", "Address 2", "3.0");
        Restaurant r3 = new Restaurant("Restaurant 3", "Address 3", "2.0");

        EditText searchBar = findViewById(R.id.search_bar);
        ArrayList<Restaurant> restaurantNames = new ArrayList<>();
        restaurantNames.add(r1);
        restaurantNames.add(r2);
        restaurantNames.add(r3);
        ListView searchResults = findViewById(R.id.search_results);

        RestaurantAdapter adapter = new RestaurantAdapter(this, R.layout.adapter_list_layout, restaurantNames);
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
            String address = getItem(position).getAddress();
            String rating = getItem(position).getRating();

            Restaurant r = new Restaurant(name, address, rating);

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
