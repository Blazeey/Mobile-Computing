package com.blazeey.sixthexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        try {
            XMLParser xmlParser = new XMLParser(new URL("http://www.feedforall.com/sample.xml"),this);
            xmlParser.startParse();
            itemList = xmlParser.getItemList();

            RSSAdapter rssAdapter = new RSSAdapter(this,R.layout.data,itemList);
            listView.setAdapter(rssAdapter);

            Toast.makeText(this, "" + itemList.size(), Toast.LENGTH_SHORT).show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}