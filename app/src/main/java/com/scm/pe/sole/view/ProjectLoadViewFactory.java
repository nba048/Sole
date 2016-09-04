package com.scm.pe.sole.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scm.pe.sole.R;
import com.shizhefei.mvc.ILoadViewFactory;
import com.shizhefei.view.vary.VaryViewHelper;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class ProjectLoadViewFactory implements ILoadViewFactory {

    @Override
    public ILoadMoreView madeLoadMoreView() {
        return new LoadMoreHelper();
    }

    @Override
    public ILoadView madeLoadView() {
        return new LoadViewHelper();
    }

    private class LoadMoreHelper implements ILoadMoreView {

        protected TextView footView;

        protected View.OnClickListener onClickRefreshListener;

        @Override
        public void init(FootViewAdder footViewHolder, View.OnClickListener onClickRefreshListener) {
            footView = (TextView) footViewHolder.addFootView(R.layout.layout_listview_foot);
            this.onClickRefreshListener = onClickRefreshListener;
            showNormal();
        }

        @Override
        public void showNormal() {
            footView.setText("点击加载更多");
            footView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showLoading() {
            footView.setText("正在加载中..");
            footView.setOnClickListener(null);
        }

        @Override
        public void showFail(Exception exception) {
            footView.setText("加载失败，点击重新加载");
            footView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showNomore() {
            footView.setText("已经加载完毕");
            footView.setOnClickListener(null);
        }

    }

    private class LoadViewHelper implements ILoadView {
        private VaryViewHelper helper;
        private View.OnClickListener onClickRefreshListener;
        private Context context;

        @Override
        public void init(View switchView, View.OnClickListener onClickRefreshListener) {
            this.context = switchView.getContext().getApplicationContext();
            this.onClickRefreshListener = onClickRefreshListener;
            helper = new VaryViewHelper(switchView);
        }

        @Override
        public void restore() {
            helper.restoreView();
        }

        @Override
        public void showLoading() {
            View layout = helper.inflate(R.layout.load_ing);
            TextView textView = (TextView) layout.findViewById(R.id.textView1);
            textView.setText("加载中...");
            helper.showLayout(layout);
        }

        @Override
        public void tipFail(Exception exception) {
            Toast.makeText(context, "网络加载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void showFail(Exception exception) {
            View layout = helper.inflate(R.layout.load_error);
            layout.setOnClickListener(onClickRefreshListener);
            helper.showLayout(layout);
        }

        @Override
        public void showEmpty() {
            View layout = helper.inflate(R.layout.load_empty);
            layout.setOnClickListener(onClickRefreshListener);
            helper.showLayout(layout);
        }

    }
}
