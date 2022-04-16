package com.example.textrepeater.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.widget.Toolbar;

import com.example.textrepeater.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initvariable();
    }

    @Override
    void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);

    }

    private void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("Text Repeater");
    }

    @Override
    void initvariable() {

    }

    public void navigateToRepeatedText(View view) {
        Intent intent = new Intent(this, RepeatedTextActivity.class);
        startActivity(intent);
    }

    public void navigateToBlankText(View view) {
        Intent intent = new Intent(this, BlankTextActivity.class);
        startActivity(intent);
    }

    public void navigateToFlipText(View view) {
        Intent intent = new Intent(this, FlipTextActivity.class);
        startActivity(intent);
    }
}