package com.jiao.button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.button.library.CustomButton;

public class MainActivity extends AppCompatActivity {

    private CustomButton mSelectedBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSelectedBtn = (CustomButton) findViewById(R.id.selected_btn);
        mSelectedBtn.setSelected(true);

    }
}
