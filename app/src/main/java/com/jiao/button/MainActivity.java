package com.jiao.button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.button.library.CustomButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomButton mSelectedBtn;

    private CustomButton mSmallButton;
    private CustomButton mNormalButton;
    private CustomButton mBigButton;
    private CustomButton mLargeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSmallButton = (CustomButton) findViewById(R.id.bt_font_size_small);
        mNormalButton = (CustomButton) findViewById(R.id.bt_font_size_normal);
        mBigButton = (CustomButton) findViewById(R.id.bt_font_size_big);
        mLargeButton = (CustomButton) findViewById(R.id.bt_font_size_large);

        mSmallButton.setOnClickListener(this);
        mNormalButton.setOnClickListener(this);
        mBigButton.setOnClickListener(this);
        mLargeButton.setOnClickListener(this);

        mSelectedBtn = (CustomButton) findViewById(R.id.selected_btn);
        mSelectedBtn.setSelected(true);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_font_size_small:
                mSmallButton.setSelected(true);
                mNormalButton.setSelected(false);
                mBigButton.setSelected(false);
                mLargeButton.setSelected(false);
                break;
            case R.id.bt_font_size_normal:
                mSmallButton.setSelected(false);
                mNormalButton.setSelected(true);
                mBigButton.setSelected(false);
                mLargeButton.setSelected(false);
                break;
            case R.id.bt_font_size_big:
                mSmallButton.setSelected(false);
                mNormalButton.setSelected(false);
                mBigButton.setSelected(true);
                mLargeButton.setSelected(false);
                break;
            case R.id.bt_font_size_large:
                mSmallButton.setSelected(false);
                mNormalButton.setSelected(false);
                mBigButton.setSelected(false);
                mLargeButton.setSelected(true);
                break;
        }
    }

}
