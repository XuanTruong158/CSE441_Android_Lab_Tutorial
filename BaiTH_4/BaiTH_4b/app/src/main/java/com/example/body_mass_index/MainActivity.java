package com.example.body_mass_index;
import java.text.DecimalFormat;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    Button btnChandoan;
    EditText editTen, edtChieucao, edtCannang, edtBMI, edtChandoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChandoan = findViewById(R.id.btnChandoan);
        edtChieucao = findViewById(R.id.edtchieucao);
        edtCannang = findViewById(R.id.edtcannang);
        edtBMI = findViewById(R.id.edtBMI);
        edtChandoan = findViewById(R.id.edtChandoan);

        btnChandoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                double H = Double.parseDouble(edtChieucao.getText() + "");
                double W = Double.parseDouble(edtCannang.getText() + "");
                double BMI = W / Math.pow(H, 2);
                String chandoan = "";

                if (BMI < 18)
                    chandoan = "Bạn gầy";
                else if (BMI < 24.9)
                    chandoan = "Bạn bình thường";
                else if (BMI < 29.9)
                    chandoan = "Bạn béo phì độ 1";
                else if (BMI < 34.9)
                    chandoan = "Bạn béo phì độ 2";
                else
                    chandoan = "Bạn béo phì độ 3";

                DecimalFormat dcf = new DecimalFormat("#.0");
                edtBMI.setText(dcf.format(BMI));
                edtChandoan.setText(chandoan);
            }
        });
    }
}
