/**
 * 
 */
package com.siprum.smart.tv.mobile.v2videoconapp.util;

import java.util.ArrayList;
import java.util.List;

import com.siprum.smart.tv.mobile.v2videoconapp.enums.V2ChannelDetailEnum;
import com.siprum.smart.tv.mobile.v2videoconapp.enums.V2ChannelCategoryEnum;


/**
 * @author arajmony
 * 
 */
public class V2ChannelUtility {
	
	
	/*
	 *  Given a Channel Category like "Tamil" this function will return all the Channels marked under the category.  Since each Tab on the UI is going to be a fragment, and one 
	 *  instance of the Fragment will be created for each Category, each of those Fragment instances would be making a call to this method with the appropriate parameter. 
	 * 
	 */
	static public List<V2ChannelDetailEnum> getAllChannelDetailEnumsForChannelCategory(V2ChannelCategoryEnum forChannelCategory){
		
		List<V2ChannelDetailEnum> list = new ArrayList<V2ChannelDetailEnum>();
	   	
		for(V2ChannelDetailEnum curr : V2ChannelDetailEnum.values()){

			   for( V2ChannelCategoryEnum chCatEnum : curr.getChannelCategories()){
				   if(chCatEnum.equals(forChannelCategory)){
					   list.add(curr);
					   break;
				   }
			   }
			
		}
		
		return list;
	}
	
	
	static public int getChannelCategoryCount(){
		return V2ChannelCategoryEnum.values().length;
	}
	
	
	
}
