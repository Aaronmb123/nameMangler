package com.bakeaaro.namemangler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mMangleButton;
    private EditText mNameEditText;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("name", String.valueOf(mNameEditText.getText()));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = (EditText) findViewById(R.id.name_edit_text);

        if (savedInstanceState != null) {
            mNameEditText.setText(String.valueOf(savedInstanceState.get("name")));
        }

        mMangleButton = (Button) findViewById(R.id.mangle_button);
        mMangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String editTextName = mNameEditText.getText().toString();

                if(editTextName.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), MangleActivity.class);
                EditText mNameEditText = (EditText) findViewById(R.id.name_edit_text);
                String name = mNameEditText.getText().toString();
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }
}
