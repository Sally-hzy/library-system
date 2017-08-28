package com.hut.JComPz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hut.dao.Dao;
import com.hut.model.BookType;

/*
 * 创建一个Map 接口
 */
public class MapPz {
	static Map map=new HashMap();
	
	public static Map getMap(){
		List list=Dao.selectBookCategory();
		for (int i=0;i<list.size();i++){
			BookType bookType=(BookType) list.get(i);
			
			Item item=new Item();
			item.setId(bookType.getId());
			item.setName(bookType.getTypeName());
			map.put(item.getId(),item);
			
		}
		return map;
		
	}

}
