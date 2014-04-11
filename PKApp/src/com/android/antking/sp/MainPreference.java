
package com.android.antking.sp;

import android.content.Context;

public class MainPreference extends PreferenceHelper {

    public MainPreference(Context context) {
        super(context, "main");
    }

    public void setPkLevel(int pk_level) {
        mSp.edit().putInt("pk_level", pk_level).commit();
    }

    public int getPkLevel() {
        return mSp.getInt("pk_level", 6);
    }

    

}
