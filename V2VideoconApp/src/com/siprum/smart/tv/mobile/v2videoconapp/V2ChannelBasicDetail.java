/**
 * 
 */
package com.siprum.smart.tv.mobile.v2videoconapp;

import java.util.ArrayList;
import java.util.List;

import com.siprum.smart.tv.mobile.v2videoconapp.enums.V2ChannelCategoryEnum;

/**
 * @author arajmony
 *
 */
public class V2ChannelBasicDetail {
	
	int channelNumber;
	String channelName;
	List<V2ChannelCategoryEnum> categories;
	
	
	
	public V2ChannelBasicDetail(int channelNumber, String channelName) {
		super();
		this.channelNumber = channelNumber;
		this.channelName = channelName;
	}



	public List<V2ChannelCategoryEnum> getCategories() {
		
		if(categories == null)
			categories = new ArrayList<V2ChannelCategoryEnum>(2);
		
		return categories;
	}




	
	

}
