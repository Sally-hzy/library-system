package com.hut.util;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.PlainDocument;
import javax.swing.text.Position;
import javax.swing.text.Segment;

public class MyDocument extends  PlainDocument {
	/*
	 * PlainDocument ά�����κ��ַ����Ե���ͨ�ĵ������ĵ���Ĭ��Ԫ�ؽṹ���ı��е��е�ӳ�䡣
	 * �� getDefaultRootElement ���ص� Element ���е�ӳ�䣬��ÿ����Ԫ�ر�ʾһ�С�
	 * ��ģ�Ͳ�ά���κ��ַ������ԣ���ÿ�ж�������һ����������Լ�����ǡ�
	 * ʹ��Ĭ�ϵĸ�Ԫ�ؿ��Կ���ִ�д��е�ƫ�����ʹ�ƫ�������е�ת��
	 */
	int maxLength = 20;

	public MyDocument(int newMaxLength) {
		super();
		maxLength = newMaxLength;
	}

	public MyDocument() {
		this(20);
	}

	// ���ظ����insertString����
	public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {
		if (getLength() + str.length() > maxLength) {// ����ٶ�������Ƴ���Ϊ10
			return;
		} else {
			super.insertString(offset, str, a);

		}
	}

}
