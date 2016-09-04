package com.scm.pe.sole.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;

import com.scm.pe.sole.R;
import com.scm.pe.sole.adapter.JokesAdapter;
import com.scm.pe.sole.entity.Jok;
import com.scm.pe.sole.model.JoksAsyncDataSource;
import com.scm.pe.sole.widget.MVCSwipeRefreshHelper;
import com.shizhefei.mvc.MVCHelper;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private MVCHelper<List<Jok>> mvcHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 设置LoadView的factory，用于创建使用者自定义的加载失败，加载中，加载更多等布局,写法参照DeFaultLoadViewFactory
        MVCHelper.setLoadViewFractory(new ProjectLoadViewFactory());

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mvcHelper = new MVCSwipeRefreshHelper<List<Jok>>(swipeRefreshLayout);
        // 设置数据源
        mvcHelper.setDataSource(new JoksAsyncDataSource());
        // 设置适配器
        mvcHelper.setAdapter(new JokesAdapter(this));
        // 加载数据
        mvcHelper.refresh();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放资源
        mvcHelper.destory();
    }
}
