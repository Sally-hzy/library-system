package com.hut.util;

import java.net.URL;

import javax.swing.ImageIcon;

import com.hut.Library;

public class CreatecdIcon {
	public static ImageIcon add(String ImageName){
		
		/*  类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。
		 * 资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，例如对数据库或搜索引擎的查询
		*/
		
		ImageIcon icon=null;
  	try {
			URL IconUrl=Library.class.getResource("/"+ImageName)  ;
			
		//	System.out.println("********IconUrl="+IconUrl);
			/*
			 * ImageIcon 使用 MediaTracker 预载根据 URL、文件名或字节数组创建的图像，以监视该图像的加载状态。 
			 */
		//	System.out.println("*********************CreatecdIcon 1 ******************");

			icon = new ImageIcon(IconUrl);
		} catch (NullPointerException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
		//	System.out.println("*********************CreatecdIcon  2******************");
			return icon;
		}
		
		
	}
	

}
