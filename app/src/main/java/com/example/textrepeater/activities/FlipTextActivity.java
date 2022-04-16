package com.example.textrepeater.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.textrepeater.R;

public class FlipTextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_text);
        initView();
        initvariable();
    }

    @Override
    void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);
    }

    @Override
    void initvariable() {

    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        //Your toolbar is now an action bar and you can use it like you always do, for example:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Flip Text");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);

    }

}