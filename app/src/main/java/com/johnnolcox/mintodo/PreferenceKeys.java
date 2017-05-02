package com.johnnolcox.mintodo;

import android.content.res.Resources;

import com.johnnolcox.mintodo.R;

public class PreferenceKeys {
    final String night_mode_pref_key;

    public PreferenceKeys(Resources resources){
        night_mode_pref_key = resources.getString(R.string.night_mode_pref_key);
    }
}
