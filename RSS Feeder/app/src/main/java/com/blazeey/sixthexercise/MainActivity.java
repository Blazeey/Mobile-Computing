package com.blazeey.sixthexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            XMLParser xmlParser = new XMLParser(new URL("http://www.bitnewz.net/rss/feed/10"));
            xmlParser.startParse();
            itemList = xmlParser.getItemList();


            Toast.makeText(this, "" + itemList.size(), Toast.LENGTH_SHORT).show();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


}
