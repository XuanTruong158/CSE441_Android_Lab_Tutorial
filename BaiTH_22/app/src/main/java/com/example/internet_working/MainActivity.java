
package com.example.internet_working;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnparse;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnparse = findViewById(R.id.btnparse);
        lv = findViewById(R.id.lv);

        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadExampleTask().execute();
            }
        });
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            XMLParser parser = new XMLParser();
            ArrayList<String> list = new ArrayList<>();
            try {
                // Link XML thật
                String xml = parser.getXmlFromUrl("https://stackoverflow.com/feeds");

                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = fc.newPullParser();
                xmlPullParser.setInput(new StringReader(xml));

                int eventType = xmlPullParser.getEventType();
                String datashow = "";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (xmlPullParser.getName().equalsIgnoreCase("title")) {
                                datashow = xmlPullParser.nextText();
                                list.add(datashow);
                            }
                            break;
                    }
                    eventType = xmlPullParser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            myadapter.addAll(result);
            myadapter.notifyDataSetChanged();
        }
    }
}
