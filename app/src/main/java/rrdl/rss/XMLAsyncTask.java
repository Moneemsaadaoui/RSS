package rrdl.rss;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by moneem on 26/02/18.
 */

public class XMLAsyncTask extends AsyncTask<String,Void,Document> {
    interface DocumentConsumer
    {
        void setXMLDocument(Document document);
    }
    private DocumentConsumer _consumer;
    public XMLAsyncTask(DocumentConsumer consumer){_consumer=consumer;}

    @Override
    protected Document doInBackground(String... strings) {
    try{
        Thread.sleep(5000);
        URL url=new URL(strings[0]);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        InputStream stream=connection.getInputStream();
        try
        {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
        }finally {
            stream.close();
        }
    }catch (Exception e){
        Log.e("XMLASYNCTASK","ERROR DOWNLOADING",e);
        throw new RuntimeException(e);
         }

    }

    @Override
    protected void onPostExecute(Document document) {
        super.onPostExecute(document);
        Log.e("XMLASYNCTASK","SUCCESS");
        _consumer.setXMLDocument(document);
    }
}
