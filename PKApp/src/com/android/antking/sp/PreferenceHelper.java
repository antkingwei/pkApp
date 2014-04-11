
package com.android.antking.sp;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class PreferenceHelper {
    protected SharedPreferences mSp;

    public PreferenceHelper(Context context, String name) {
        mSp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return mSp;
    }
}
