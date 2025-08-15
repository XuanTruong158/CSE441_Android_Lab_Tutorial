package com.example.tao_copy_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database=null;
    String DATABASE_NAME="qlsach.db";
    //Khai báo ListView
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Goi hàm copy CSDL từ assets vào thư mục Databases
        processCopy();
        //Tạo CSDL để mở và hiển thị lên ListView
        database = openOrCreateDatabase("qlsach.db", MODE_PRIVATE, null);
        //Tạo ListView
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);
        //Thực thi câu lệnh SQL để hiển thị lên ListView
        Cursor c = database.query("tbsach", null, null, null, null, null,null);
        c.moveToFirst();
        String data = "";
        while (c.isAfterLast() == false) {
            data = c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2);
            mylist.add(data);
            c.moveToNext();
        }
        c.close();
        myadapter.notifyDataSetChanged();
    }

    private void processCopy() {
        //dbFile is a variable representing a file
        File dbFile = getDatabasePath(DATABASE_NAME);
        //if the file doesn't exist, create it
        if (!dbFile.exists()) {
            try {
                //Copy the database from the assets folder to the databases folder
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                //if there's an error, show a toast with the error message
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    private void CopyDataBaseFromAsset() {
        // TODO Auto-generated method stub
        try {
            //open the input stream to read the database file from the assets folder
            InputStream myInput = getAssets().open(DATABASE_NAME);
            //Path to the database on the phone's storage
            String outFileName = getDatabasePath();
            //if the path doesn't exist, create the directory
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }
            //Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            //Transfer bytes from the inputfile to the outputfile
            //Truyền dữ liệu từ input đầu vào sang output
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            //close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}