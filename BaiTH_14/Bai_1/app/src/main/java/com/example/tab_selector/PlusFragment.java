package com.example.tab_selector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PlusFragment extends Fragment {

    ArrayList<String> list;

    public PlusFragment(ArrayList<String> list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manhinh1, container, false);

        EditText edta = view.findViewById(R.id.edta);
        EditText edtb = view.findViewById(R.id.edtb);
        Button btncong = view.findViewById(R.id.btncong);

        btncong.setOnClickListener(v -> {
            try {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                String c = a + " + " + b + " = " + (a + b);
                list.add(c);
                edta.setText("");
                edtb.setText("");
            } catch (NumberFormatException ignored) {}
        });

        return view;
    }
}
