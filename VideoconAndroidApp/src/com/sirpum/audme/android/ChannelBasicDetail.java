/**
 * 
 */
package com.sirpum.audme.android;

/**
 * @author arajmony
 *
 */
public class ChannelBasicDetail {
	
	int channelNumber;
	String channelName;
	ChannelCategory[] categories;
	
	
	
	public ChannelBasicDetail(int channelNumber, String channelName) {
		super();
		this.channelNumber = channelNumber;
		this.channelName = channelName;
	}



	public ChannelCategory[] getCategories() {
		return categories;
	}
	
	
	

}

enum ChannelCategory{
	FAVOURITES,TAMIL,HD_CHANNELS,MALAYALAM,NEWS,MOVIES_ENGLISH,SPORTS
}