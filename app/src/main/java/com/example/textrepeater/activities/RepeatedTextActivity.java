package com.example.textrepeater.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.textrepeater.R;

public class RepeatedTextActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEnterTextET;
    private EditText mRepetitionLimitET;
    private CheckBox mNewLineCB;
    private TextView mResetTV;
    private TextView mGenerateTV;
    private ImageView mShareIV;
    private ImageView mCopyIV;
    private TextView mResultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeated_text);
        initView();
        initvariable();
    }

    @Override
    void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);
        mEnterTextET = (EditText) findViewById(R.id.et_enter_text);
        mRepetitionLimitET = (EditText) findViewById(R.id.et_repetition_limit);
        mNewLineCB = (CheckBox) findViewById(R.id.cb_new_line);
        mResetTV = (TextView) findViewById(R.id.tv_reset);
        mGenerateTV = (TextView) findViewById(R.id.tv_generate);
        mCopyIV = (ImageView) findViewById(R.id.img_copy);
        mShareIV = (ImageView) findViewById(R.id.img_share);
        mCopyIV.setOnClickListener(this);
        mShareIV.setOnClickListener(this);
        mResetTV.setOnClickListener(this);
        mGenerateTV.setOnClickListener(this);
        mResultTV = (TextView) findViewById(R.id.tv_result);
        mResultTV.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        //Your toolbar is now an action bar and you can use it like you always do, for example:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Repeated Text");
    }

    @Override
    void initvariable() {

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

            case R.id.tv_reset:
                resetFields();
                break;
            case R.id.tv_generate:
                validateFields();
                break;
            case R.id.img_copy:
                copyText();
                break;
            case R.id.img_share:
                shareContent();
                break;
        }
    }

    private void resetFields() {
        mEnterTextET.setText("");
        mRepetitionLimitET.setText("");
        mResultTV.setText("");
        mNewLineCB.setChecked(false);
    }

    private void validateFields() {
        if (mEnterTextET.getText().toString().length() == 0) {
            mEnterTextET.requestFocus();
            Toast.makeText(this, "Enter some text !!", Toast.LENGTH_SHORT).show();

        } else if (mRepetitionLimitET.getText().toString().length() == 0) {
            mRepetitionLimitET.requestFocus();
            Toast.makeText(this, "Enter repetition limit !!", Toast.LENGTH_SHORT).show();

        } else {
            generateRepetitionText(mEnterTextET.getText().toString().trim(), mRepetitionLimitET.getText().toString().trim());
        }

    }

    private void generateRepetitionText(String text, String repetitionLimit) {
        mResultTV.requestFocus();
        mResultTV.setText("");
        int limit = Integer.parseInt(repetitionLimit);
        StringBuilder result = new StringBuilder();
        boolean mIsNewLine = mNewLineCB.isChecked();
        for (int i = 0; i < limit; i++) {
            if (mIsNewLine) {
                result = result.append(text).append("\n");
            } else {
                result = result.append(text).append(" ");
            }

        }
        mResultTV.setText(result.toString());
    }

    private void shareContent() {
        if (mResultTV.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "No content to share !!", Toast.LENGTH_SHORT).show();
        } else {
            /*Create an ACTION_SEND Intent*/
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            /*This will be the actual content you wish you share.*/
            String shareBody = mResultTV.getText().toString().trim();
            /*The type of the content is text, obviously.*/
            intent.setType("text/plain");
            /*Applying information Subject and Body.*/
            intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Using: ");
            intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            /*Fire!*/
            startActivity(Intent.createChooser(intent, "Share Using: "));
        }
    }

    private void copyText() {
        if (mResultTV.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "No content to copy !!", Toast.LENGTH_SHORT).show();
        } else {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", mResultTV.getText().toString().trim());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "text copied", Toast.LENGTH_LONG).show();
        }
    }
}