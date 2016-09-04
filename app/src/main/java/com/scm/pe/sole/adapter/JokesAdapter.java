package com.scm.pe.sole.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.scm.pe.sole.R;
import com.scm.pe.sole.entity.Jok;
import com.scm.pe.sole.utils.NetVolley;
import com.shizhefei.mvc.IDataAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class JokesAdapter extends BaseAdapter implements IDataAdapter<List<Jok>> {

    private final List<Jok> joks;
    private final ImageLoader mImageLoader;

    private Context context;

    public JokesAdapter(Context context){
       joks =  new ArrayList<Jok>();
        this.context = context;
        mImageLoader = NetVolley.getImageLoader();
    }

    @Override
    public int getCount() {
        return joks.size();
    }

    @Override
    public Jok getItem(int i) {
        return joks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
         InnerHorder horder = null;
        if (v == null){
            horder = new InnerHorder();
            v = LayoutInflater.from(context).inflate(R.layout.item_jokes_layout,null);
            horder.itemJokesTitle = (TextView)v.findViewById( R.id.item_jokes_title );
            horder.itemJokesImage = (ImageView)v.findViewById(R.id.item_jokes_image);
            horder.itemJokesTime = (TextView)v.findViewById(R.id.item_jokes_time);
            v.setTag(horder);
        }else {
            horder = (InnerHorder) v.getTag();
        }
        horder.itemJokesTime.setText(joks.get(i).getUpdatetime());
        horder.itemJokesTitle.setText(joks.get(i).getContent());

        final InnerHorder finalHorder = horder;
        mImageLoader.get(joks.get(i).getUrl(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (b){
                    finalHorder.itemJokesImage.setImageBitmap(imageContainer.getBitmap());
                    notifyDataSetChanged();
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        return v;
    }

    @Override
    public void notifyDataChanged(List<Jok> data, boolean isRefresh) {
        if (isRefresh) {
            joks.clear();
        }
        joks.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public List<Jok> getData() {
        return joks;
    }



    private class InnerHorder {
        private TextView itemJokesTitle;
        private ImageView itemJokesImage;
        private TextView itemJokesTime;

    }
}
