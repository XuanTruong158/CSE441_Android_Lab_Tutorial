package com.example.register_personal_info;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTen, editCMND, editBosung;
    CheckBox chkDocBao, chkDocSach, chkDocCoding;
    RadioGroup group;
    Button btnGuiTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ View
        editTen = findViewById(R.id.editTen);
        editCMND = findViewById(R.id.editCMND);
        editBosung = findViewById(R.id.editBosung);
        chkDocBao = findViewById(R.id.chkDocBao);
        chkDocSach = findViewById(R.id.chkDocSach);
        chkDocCoding = findViewById(R.id.chkDocCoding);
        group = findViewById(R.id.radioGroup1);
        btnGuiTT = findViewById(R.id.btnGuiTT);

        btnGuiTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInformation();
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitDialog();
            }
        });

    }

    private void showInformation() {
        String ten = editTen.getText().toString().trim();
        if (ten.length() < 3) {
            editTen.requestFocus();
            editTen.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String cmnd = editCMND.getText().toString().trim();
        if (cmnd.length() != 9 || !cmnd.matches("\\d+")) {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 chữ số", Toast.LENGTH_LONG).show();
            return;
        }

        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        String bangCap = rad.getText().toString();

        String soThich = "";
        if (chkDocBao.isChecked()) soThich += chkDocBao.getText() + " - ";
        if (chkDocSach.isChecked()) soThich += chkDocSach.getText() + " - ";
        if (chkDocCoding.isChecked()) soThich += chkDocCoding.getText();

        if (soThich.isEmpty()) {
            Toast.makeText(this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_LONG).show();
            return;
        }

        if (soThich.endsWith(" - ")) {
            soThich = soThich.substring(0, soThich.length() - 3);
        }

        String boSung = editBosung.getText().toString().trim();
        String msg = ten + "\n" + cmnd + "\n" + bangCap + "\n" + soThich
                + "\n-------------------------------------\nThông tin bổ sung:\n" + boSung + "\n-------------------------------------\n";

        // Inflate layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_info, null);
        TextView txtMessage = dialogView.findViewById(R.id.txtMessage);
        txtMessage.setText(msg);

        // Tạo AlertDialog tùy chỉnh
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

        Button btnClose = dialogView.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }




    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_angry_bird);
        builder.setTitle("Question");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("NO", null);
        builder.show();
    }



}
