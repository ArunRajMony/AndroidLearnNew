package com.sirpum.audme.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    
  

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(190, 100));
            //imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); 
            imageView.setPadding(1, 1, 1, 1);
            
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        //set the Id since we want to have multiple tabs on the mobile screen, a channel icon can appear in different tabs or even if it appears in 
        //only one tab, the index position cannot be used to retrieve the channel's 3 digit #. So here while creating the view for each image icon (view)
        //we will set the "id" with the corresponding channel #.  basically adding meta-data to each icon. other option to set meta-data is via the setTag method
        
        int chNum = ChannelUtility.chNumImgIndexList[position];
        Integer ChNumInteger = Integer.valueOf(chNum);
        imageView.setId(chNum); 
        //setting tag with channel number and ch name
        ChannelBasicDetail tag = new ChannelBasicDetail(chNum, ChannelUtility.chNameImgIndexList[position]);
        if(ChannelUtility.chCategoryMap.containsKey(ChNumInteger)){
        	tag.getCategories().addAll(ChannelUtility.chCategoryMap.get(ChNumInteger));
        }
        
        imageView.setTag(tag);
      
        
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
    		R.drawable.a_242  ,
    		R.drawable.a_248  ,
    		R.drawable.a_256  ,
    		R.drawable.a_351  ,
    		R.drawable.a_353  ,
    		R.drawable.a_355  ,
    		R.drawable.a_357  ,
    		R.drawable.a_359  ,
    		R.drawable.a_361  ,
    		R.drawable.a_381  ,
    		R.drawable.a_382  ,
    		R.drawable.a_402  ,
    		R.drawable.a_404  ,
    		R.drawable.a_408  ,
    		R.drawable.a_424  ,
    		R.drawable.a_560  ,
    		R.drawable.a_602  ,
    		R.drawable.a_603  ,
    		R.drawable.a_604  ,
    		R.drawable.a_606  ,
    		R.drawable.a_640  ,
    		R.drawable.a_653  ,
    		R.drawable.a_654  ,
    		R.drawable.a_797  ,
    		R.drawable.a_798  ,
    		R.drawable.a_799  ,
    		R.drawable.a_800  ,
    		R.drawable.a_801  ,
    		R.drawable.a_802  ,
    		R.drawable.a_803  ,
    		R.drawable.a_804  ,
    		R.drawable.a_805  ,
    		R.drawable.a_806  ,
    		R.drawable.a_807  ,
    		R.drawable.a_808  ,
    		R.drawable.a_809  ,
    		R.drawable.a_810  ,
    		R.drawable.a_811  ,
    		R.drawable.a_812  ,
    		R.drawable.a_813  ,
    		R.drawable.a_814  ,
    		R.drawable.a_815  ,
    		R.drawable.a_816  ,
    		R.drawable.a_817  ,
    		R.drawable.a_818  ,
    		R.drawable.a_819  ,
    		R.drawable.a_820  ,
    		R.drawable.a_821  ,
    		R.drawable.a_822  ,
    		R.drawable.a_823  ,
    		R.drawable.a_824  ,
    		R.drawable.a_825  ,
    		R.drawable.a_826  ,
    		R.drawable.a_827  ,
    		R.drawable.a_828  ,
    		R.drawable.a_829  ,
    		R.drawable.a_830  ,
    		R.drawable.a_851  ,
    		R.drawable.a_852  ,
    		R.drawable.a_853  ,
    		R.drawable.a_854  ,
    		R.drawable.a_855  ,
    		R.drawable.a_856  ,
    		R.drawable.a_857  ,
    		R.drawable.a_858  ,
    		R.drawable.a_859  ,
    		R.drawable.a_860  ,
    		R.drawable.a_861  ,
    		R.drawable.a_862  ,
    		R.drawable.a_863  ,
    		R.drawable.a_864  ,
    		R.drawable.a_865  ,
    		R.drawable.a_866  ,
    		R.drawable.a_868  ,
    		R.drawable.a_869  ,
    		R.drawable.a_870  
    };
}