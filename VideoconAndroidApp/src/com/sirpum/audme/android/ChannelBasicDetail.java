/**
 * 
 */
package com.sirpum.audme.android;

import java.util.ArrayList;
import java.util.List;

/**
 * @author arajmony
 *
 */
public class ChannelBasicDetail {
	
	int channelNumber;
	String channelName;
	List<ChannelCategory> categories;
	
	
	
	public ChannelBasicDetail(int channelNumber, String channelName) {
		super();
		this.channelNumber = channelNumber;
		this.channelName = channelName;
	}



	public List<ChannelCategory> getCategories() {
		
		if(categories == null)
			categories = new ArrayList<ChannelCategory>(2);
		
		return categories;
	}




	
	

}
