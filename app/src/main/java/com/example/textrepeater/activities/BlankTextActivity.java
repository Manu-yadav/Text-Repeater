package com.example.textrepeater.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.textrepeater.R;

public class BlankTextActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEnterTextET;
    private CheckBox mNewLineCB;
    private TextView mShareTV;
    private TextView mCopyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank_text);
        initView();
        initvariable();
    }

    @Override
    void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);
        mEnterTextET = (EditText) findViewById(R.id.et_enter_text);
        mNewLineCB = (CheckBox) findViewById(R.id.cb_new_line);
        mShareTV = (TextView) findViewById(R.id.tv_share);
        mCopyTV = (TextView) findViewById(R.id.tv_copy);

        mShareTV.setOnClickListener(this);
        mCopyTV.setOnClickListener(this);
    }

    @Override
    void initvariable() {

    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        //Your toolbar is now an action bar and you can use it like you always do, for example:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Blank Text");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_copy:
                copyText();
                break;
            case R.id.tv_share:
                shareContent();
                break;
        }
    }

    private void shareContent() {
        if (mEnterTextET.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "No content to share !!", Toast.LENGTH_SHORT).show();
        } else {
            String blankText = getBlankText();
            /*Create an ACTION_SEND Intent*/
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            /*This will be the actual content you wish you share.*/
            /*String shareBody = blankText;*/
            /*The type of the content is text, obviously.*/
            intent.setType("text/plain");
            /*Applying information Subject and Body.*/
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Using: ");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, blankText);
            /*Fire!*/
            startActivity(Intent.createChooser(intent, "Share Using: "));
            Toast.makeText(this, blankText, Toast.LENGTH_LONG).show();
        }
    }

    private String getBlankText() {
        StringBuilder result = new StringBuilder();
        int count = Integer.parseInt(mEnterTextET.getText().toString().trim());
        boolean mIsNewLine = mNewLineCB.isChecked();
        while (count > 0) {
            if (mIsNewLine) {
                result.append(" " + "\n");
            } else {
                result.append(" ");
            }
            count--;
        }
        return result.toString();
    }

    private void copyText() {
        if (mEnterTextET.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "No content to copy !!", Toast.LENGTH_SHORT).show();
        } else {
            String blankText = getBlankText();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", blankText);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "text copied " + blankText, Toast.LENGTH_LONG).show();
        }
    }
}