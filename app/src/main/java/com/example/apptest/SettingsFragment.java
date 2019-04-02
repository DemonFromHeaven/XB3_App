package com.example.apptest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

/**
 * The Preference Fragment which shows the Preferences as a List and handles the Dialogs for the
 * Preferences.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_preferences);

        Preference licensePref = (Preference)findPreference("pref_key_licenses");
        licensePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(getActivity(), OpenSourceLibsActivity.class));
                return true;
            }
        });

        Preference logoutPref = (Preference)findPreference("pref_key_logout");
        logoutPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));

                return true;
            }
        });

        /**
         * Bind preference summary to value for lists and sorting list preferences
         */
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_name_sort_order_lists)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_name_default_home_state)));

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        setPreferenceSummary(preference, newValue);

        if (preference.getKey().equals("pref_sort_order_lists")){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor spe = sharedPref.edit();
            spe.putString(Constants.KEY_PREF_SORT_ORDER_LISTS, newValue.toString()).apply();
        }

        if (preference.getKey().equals("pref_default_home_state")){
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor spe = sharedPref.edit();
            spe.putString(Constants.KEY_PREF_HOME_STATE, newValue.toString()).apply();
        }
        return true;
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        /* Set the listener to watch for value changes. */
        preference.setOnPreferenceChangeListener(this);
        /* Trigger the listener immediately with the preference's current value. */
        setPreferenceSummary(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private void setPreferenceSummary(Preference preference, Object value){
        String stringValue = value.toString();

        if (preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);

            if (prefIndex >=0){
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }
    }

}
