package rrdl.rss;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by moneem on 26/02/18.
 */

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.ArticleViewHolder>implements XMLAsyncTask.DocumentConsumer {
    private Document _document=null;

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cell,parent,false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Element item= (Element) _document.getElementsByTagName("item").item(position);
        holder.setElement(item);
    }

    @Override
    public int getItemCount() {
       if(_document!=null)
       {return _document.getElementsByTagName("item").getLength();}
       else return 0;
    }

    @Override
    public void setXMLDocument(Document document) {
        _document=document;
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private Element _currentElement;
        public ArticleViewHolder(View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.title);
        }
        public void setElement(Element element)
        {
            _currentElement=element;
            mTitle.setText(element.getElementsByTagName("title").item(0).getTextContent());
        }
    }
}
