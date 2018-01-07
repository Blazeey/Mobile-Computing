package com.blazeey.sixthexercise;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by venki on 7/1/18.
 */

public class XMLParser {
    private List<Item> itemList;
    private URL url;
    public XMLParser(URL url) {
        this.itemList = new ArrayList<>();
        this.url = url;
    }

    public void startParse(){

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(url.toString());
        Log.v("Back","started back");
    }

    public void parseXML(XmlPullParser xmlPullParser){

        String element="",title="",link="",description="",value="";

        try {
            while (xmlPullParser.next()!=XmlPullParser.END_DOCUMENT){
                int event = xmlPullParser.getEventType();
                element = xmlPullParser.getName();
                Log.v("XML",xmlPullParser.getName());
                switch (event){
                    case XmlPullParser.TEXT:
                        Log.v("EVENT","Text");
                        value=xmlPullParser.getText();
                        xmlPullParser.nextTag();
                        break;
                    case XmlPullParser.END_TAG:
                        Log.v("EVENT","End Tag");
                        switch (element){
                            case "title":
                                title = value;
                                break;
                            case "link":
                                link = value;
                                break;
                            case "description":
                                description = value;
                                break;
                            case "item":
                                Item item = new Item(title,description, URI.create(link));
                                itemList.add(item);
                                break;
                        }
                        break;
                    default:
                        Log.v("Event","Something Else");
                }
            }
        } catch (XmlPullParserException e) {
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
                urlConnection.setReadTimeout(3000);
                urlConnection.setConnectTimeout(3000);

                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                xmlPullParser.setInput(inputStream,null);
//                xmlPullParser.nextTag();
                Log.v("IS",String.valueOf(inputStream.read()));
                parseXML(xmlPullParser);

                inputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
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
