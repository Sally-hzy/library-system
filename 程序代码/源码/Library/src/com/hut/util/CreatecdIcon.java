package com.hut.util;

import java.net.URL;

import javax.swing.ImageIcon;

import com.hut.Library;

public class CreatecdIcon {
	public static ImageIcon add(String ImageName){
		
		/*  �� URL ����һ��ͳһ��Դ��λ��������ָ����������Դ����ָ�롣
		 * ��Դ�����Ǽ򵥵��ļ���Ŀ¼��Ҳ�����ǶԸ�Ϊ���ӵĶ�������ã���������ݿ����������Ĳ�ѯ
		*/
		
		ImageIcon icon=null;
  	try {
			URL IconUrl=Library.class.getResource("/"+ImageName)  ;
			
		//	System.out.println("********IconUrl="+IconUrl);
			/*
			 * ImageIcon ʹ�� MediaTracker Ԥ�ظ��� URL���ļ������ֽ����鴴����ͼ���Լ��Ӹ�ͼ��ļ���״̬�� 
			 */
		//	System.out.println("*********************CreatecdIcon 1 ******************");

			icon = new ImageIcon(IconUrl);
		} catch (NullPointerException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
		//	System.out.println("*********************CreatecdIcon  2******************");
			return icon;
		}
		
		
	}
	

}
