package com.example.tabsone;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	
	  private Context mContext;
	    
	  

	    public ListAdapter(Context c) {
	        mContext = c;
	    }
	    
	    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)  {
		// TODO Auto-generated method stub
		TextView textView ;
		if(convertView == null){
			textView = new TextView(mContext);
			textView.setText("some " + position);
		}
		else
		{
			textView = (TextView)convertView;
			
		}
		
		
		
		
		return textView;
	}

}
