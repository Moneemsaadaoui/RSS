package rrdl.rss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("METAL NEWS");
        //Intializing widgets
        mRv=findViewById(R.id.list);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        RssAdapter adapter=new RssAdapter();
        mRv.setAdapter(adapter);
        XMLAsyncTask task=new XMLAsyncTask(adapter);
        task.execute("http://metalasfuck.net/zine/feeds/articles_news_reviews");
    }
}
