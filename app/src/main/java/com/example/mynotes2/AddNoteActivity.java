package com.example.mynotes2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    private EditText edTitle, edDescription;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        checkIsEdit();
    }

    private void checkIsEdit() {
        if(getIntent().getBooleanExtra("isEdit",false)){
            edTitle.setText(getIntent().getStringExtra("title"));
            edDescription.setText(getIntent().getStringExtra("description"));
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = edTitle.getText().toString();
                    String description = edDescription.getText().toString();
                    Intent intent = new Intent();
                    intent.putExtra("title", title);
                    intent.putExtra("description", description);
                    intent.putExtra("pos", getIntent().getIntExtra("pos",0));
                    intent.putExtra("isEdit",true);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = edTitle.getText().toString().trim();
                    String description = edDescription.getText().toString().trim();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("title", title);
                    returnIntent.putExtra("description", description);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            });
        }
    }

    private void initViews() {
    edTitle = findViewById(R.id.et_title);
    edDescription = findViewById(R.id.et_description);
    btnSave = findViewById(R.id.btn_save    );


    }


}