package com.scm.pe.sole.model;

import android.util.Log;

import com.google.gson.Gson;
import com.scm.pe.sole.entity.Jok;
import com.scm.pe.sole.entity.root.RootJok;
import com.scm.pe.sole.utils.HttpUtils;
import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;

import java.util.List;

import static com.scm.pe.sole.entity.V.URL_JOK_INTERFACE;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class JoksAsyncDataSource implements IAsyncDataSource<List<Jok>> {

    private int page = 1;
    private int maxPage = 30;

    @Override
    public RequestHandle refresh(ResponseSender<List<Jok>> sender) throws Exception {
        return loadJoks(sender,page);
    }

    @Override
    public RequestHandle loadMore(ResponseSender<List<Jok>> sender) throws Exception {
        return loadJoks(sender,++page);
    }

    @Override
    public boolean hasMore() {
        return page < maxPage;
    }

    private RequestHandle loadJoks(final ResponseSender<List<Jok>> sender, final int page) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RootJok bean = null;
                try {
                    bean = new Gson().fromJson(HttpUtils.executeGet(URL_JOK_INTERFACE + page), RootJok.class);
                    sender.sendData(bean.getResult().getData());
                } catch (Exception e) {
                    sender.sendError(e);
                }
            }
        }).start();

        return new RequestHandle() {
            @Override
            public void cancle() {
                Log.e("loadJoks"," - - > cancle");
            }

            @Override
            public boolean isRunning() {
                return true;
            }
        };
    }
}
