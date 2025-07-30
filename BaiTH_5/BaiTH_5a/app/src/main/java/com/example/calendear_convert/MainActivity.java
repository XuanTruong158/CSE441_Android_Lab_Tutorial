package com.example.calendear_convert;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editnamduonglich;
    Button button1;
    TextView tvNamAm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editnamduonglich = findViewById(R.id.editnamduonglich);
        button1 = findViewById(R.id.button1);
        tvNamAm = findViewById(R.id.textView5);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int can = Integer.parseInt(editnamduonglich.getText().toString()) %10;
                int chi = Integer.parseInt(editnamduonglich.getText().toString()) %12;
                String txtcan = "", txtchi = "";
                switch (can){
                    case 0:
                        txtcan = "Canh";
                        break;
                    case 1:
                        txtcan = "Tân";
                        break;
                    case 2:
                        txtcan = "Nhâm";
                        break;
                    case 3:
                        txtcan = "Quý";
                        break;
                    case 4:
                        txtcan = "Giáp";
                        break;
                    case 5:
                        txtcan = "Ất";
                        break;
                    case 6:
                        txtcan = "Bính";
                        break;
                    case 7:
                        txtcan = "Đinh";
                        break;
                    case 8:
                        txtcan = "Mậu";
                        break;
                    case 9:
                        txtcan = "Kỷ";
                        break;
                }

                switch (chi){
                    case 0:
                        txtchi = "Thân";
                        break;
                    case 1:
                        txtchi = "Dậu";
                        break;
                    case 2:
                        txtchi = "Tuất";
                        break;
                    case 3:
                        txtchi = "Hợi";
                        break;
                    case 4:
                        txtchi = "Tý";
                        break;
                    case 5:
                        txtchi = "Sửu";
                        break;
                    case 6:
                        txtchi = "Dần";
                        break;
                    case 7:
                        txtchi = "Mẹo";
                        break;
                    case 8:
                        txtchi = "Thìn";
                        break;
                    case 9:
                        txtchi = "Tỵ";
                        break;
                    case 10:
                        txtchi = "Ngọ";
                        break;
                    case 11:
                        txtchi = "Mùi";
                        break;
                }

                tvNamAm.setText(txtcan+" "+txtchi);
            }
        });
    }
}