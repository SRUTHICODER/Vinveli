package com.example.vinveli;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.buttonDownloadImage);
        btn2 = findViewById(R.id.buttonMissionSummary);
        rg = findViewById(R.id.radioGroupMissions);

        // Insert sample mission data into the database


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this,
                                    "No answer has been selected",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else {
                    RadioButton radioButton = findViewById(selectedId);
                    Toast.makeText(MainActivity.this,
                                    radioButton.getText(),
                                    Toast.LENGTH_SHORT)
                            .show();
                    openActivity2((String) radioButton.getText());
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rg.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this,
                                    "No answer has been selected",
                                    Toast.LENGTH_SHORT)
                            .show();
                } else {
                    RadioButton radioButton = findViewById(selectedId);
                    Toast.makeText(MainActivity.this,
                                    radioButton.getText(),
                                    Toast.LENGTH_SHORT)
                            .show();
                    openActivity3((String) radioButton.getText());
                }
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
            }
        });
    }

    public void openActivity2(String str) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("mission", str);
        startActivity(intent);
    }

    public void openActivity3(String str) {
        Intent intent = new Intent(this, MainActivity3.class);
        intent.putExtra("mission", str);
        startActivity(intent);
    }

    // Insert sample mission data into the database

}
