package com.wangbo.www.yimiyoupin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import com.wangbo.www.yimiyoupin.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CardAdapter<T> extends BaseAdapter {
	public Context mContext = null;
	public List<T> mData = null;
	public LayoutInflater inflater = null;

	public CardAdapter(Context context,List<T> mData) {
		this.mContext=context;
		this.mData=mData;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public CardAdapter(Context context) {
		this.mContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FrameLayout wrapper = (FrameLayout) convertView;
		View cardView;
		View convertedCardView;
		if (wrapper == null) {
			wrapper = new FrameLayout(mContext);
			wrapper.setBackgroundResource(R.drawable.card_background_shadow);
			cardView = getCardView(position, null, wrapper);
			wrapper.addView(cardView);
		} else {
			cardView = wrapper.getChildAt(0);
			convertedCardView = getCardView(position, cardView, wrapper);
			//要先删除，然后再添加，否则界面不更新
			wrapper.removeView(cardView);
			wrapper.addView(convertedCardView);
			if (convertedCardView != cardView) {
			}
		}
		return wrapper;
	}

	protected abstract View getCardView(int position, View convertView, ViewGroup parent);

	public void addAll(List<T> items){
		mData.addAll(items);
	}
	
	@Override
	public T getItem(int position) {
		return mData.get(position);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).hashCode();
	}

	public Context getContext() {
		return mContext;
	}
	
	public void clear(){
		if(mData != null){
			mData.clear();
		}
	}
}
