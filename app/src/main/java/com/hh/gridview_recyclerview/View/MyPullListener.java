package com.hh.gridview_recyclerview.View;

import android.os.Handler;
import android.os.Message;


public class MyPullListener implements PullToRefreshLayout.OnPullListener {

	@Override
	public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
		pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
		// 下拉刷新操作
//		new Handler() {
//			@Override
//			public void handleMessage(Message msg) {
//				// 千万别忘了告诉控件刷新完毕了哦！
//				pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
//			}
//		}.sendEmptyMessageDelayed(0, 3000);
	}

	@Override
	public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
		// 加载操作
		new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// 千万别忘了告诉控件加载完毕了哦！
				pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
			}
		}.sendEmptyMessageDelayed(0, 3000);
	}

}
