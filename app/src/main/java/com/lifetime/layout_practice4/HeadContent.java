package com.lifetime.layout_practice4;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class HeadContent extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_row);

        editText = findViewById(R.id.edit_text);
        editText.getBackground().setAlpha(128);
    }
}
