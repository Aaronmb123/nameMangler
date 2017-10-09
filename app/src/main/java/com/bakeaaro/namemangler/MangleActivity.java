package com.bakeaaro.namemangler;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MangleActivity extends AppCompatActivity {

    private Button mResetButton;
    private Button mReMangleButton;
    private TextView mNameTextView;
    private ArrayList<String> mNamesArray;
    private String nameToMangle;
    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangler);

        mNameTextView = (TextView) findViewById(R.id.name_text_view);

        //TODO use String Array Resource
        mNamesArray = new ArrayList<>();
        mNamesArray.add("the Great");
        mNamesArray.add("the Magnificent");
        mNamesArray.add("the Jerk");
        mNamesArray.add("the Orange");
        mNamesArray.add("the Man");

        nameToMangle = getIntent().getStringExtra("name");

        if (savedInstanceState != null) {
            index = (int) savedInstanceState.get("index");
            nameToMangle = (String) savedInstanceState.get("nameToMangle");
            String fullName = nameToMangle + " " + mNamesArray.get(index);
            mNameTextView.setText(fullName);
        } else {
            mNameTextView.setText(mangleName());
        }

        mReMangleButton = (Button) findViewById(R.id.re_mangle_button);
        mReMangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameTextView.setText(mangleName());
            }
        });

        mResetButton = (Button) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private String mangleName() {
        index = new Random().nextInt(5);
        String appendToName = String.valueOf(mNamesArray.get(index));
        return nameToMangle + " " + appendToName;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("nameToMangle", nameToMangle);
        outState.putSerializable("index", index);
        super.onSaveInstanceState(outState);

    }

}
