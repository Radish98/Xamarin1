package com.example.xamarin1;

import android.util.Log;

public class justClass implements Test {
    @Override
    public void sum(int a, int b) {
        Log.d("TAG", "test" + " "+ a+b );
    }
}
