package com.blazeey.sixthexercise;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private List<Item> itemList;
    private URL url;
    private Context context;

    public XMLParser(URL url, Context context) {
        this.itemList = new ArrayList<>();
        this.url = url;
        this.context = context;
    }

    public void startParse(){
//
//        BackgroundTask backgroundTask = new BackgroundTask();
//        backgroundTask.execute(url.toString());
//        Log.v("Back","started back");

        try {
            InputStream inputStream = context.getAssets().open("SampleRSS.xml");
            parseXML(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseXML(InputStream inputStream){

        String element="",title="",link="",description="",value="";
        Boolean isItem = false;

        try {
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);
//            xmlPullParser.nextTag();
            int event = xmlPullParser.getEventType();
            while (event!=XmlPullParser.END_DOCUMENT){

                if(event == XmlPullParser.START_DOCUMENT){
                    Log.v("Event","Start Document");
                    event = xmlPullParser.next();
                }

                if(event == XmlPullParser.START_TAG){
                    if(xmlPullParser.getName().equals("item")){
//                        Log.v("TAG","item");
                        isItem = true;
                        Log.v("TAG",xmlPullParser.getName());
                    }
                    if(xmlPullParser.getName().equals("title")){
                        element = "title";
                    }
                    if(xmlPullParser.getName().equals("link")){
                        element = "link";
                    }
                    if(xmlPullParser.getName().equals("description")){
                        element = "description";
                    }
                }

                if(event == XmlPullParser.END_TAG){
                    if(xmlPullParser.getName().equals("item")){

                        Log.v("TAG","item");
                        Item item = new Item(title,description,link);
                        itemList.add(item);
                        Log.v("ITEM ADDED",item.toString()+item.toString().length());
                        isItem = false;
                    }
                }

                if(event == XmlPullParser.TEXT){
                    if(element.equals("title")){
                        Log.v("TAG","title");
                        title = xmlPullParser.getText();
                        Log.v("Text",xmlPullParser.getText());
                        element = "";
                    }
                    else if(element.equals("link")){
                        Log.v("TAG","link");
                        link = xmlPullParser.getText();
                        Log.v("Text",xmlPullParser.getText());
                        element = "";
                    }
                    else if(element.equals("description")){
                        Log.v("TAG","description");
                        description = xmlPullParser.getText();
                        Log.v("Text",xmlPullParser.getText());
                        element = "";
                    }
                }

                if(event == XmlPullParser.END_DOCUMENT){
                    Log.v("Event","End Document");
                }
                event = xmlPullParser.next();


            }
            Log.v("List",""+itemList.size());
        } catch (XmlPullParserException e) {
            Log.v("Error",e.getMessage());
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public class BackgroundTask extends AsyncTask<String,Integer,Void> {

        private XmlPullParserFactory xmlPullParserFactory;
        private XmlPullParser xmlPullParser;

        @Override
        protected void onPreExecute() {
            try {

                xmlPullParserFactory = XmlPullParserFactory.newInstance();
                xmlPullParser = xmlPullParserFactory.newPullParser();
                xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);

            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.v("Back","done");
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                Log.v("URL",url.toString());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(10000);

                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                byte[] b = new byte[1000];
                inputStream.read(b);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String text = bufferedReader.readLine();
                while(!text.isEmpty()) {
                    Log.v("Data", text);
                    text = bufferedReader.readLine();
                }
                parseXML(inputStream);

                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}