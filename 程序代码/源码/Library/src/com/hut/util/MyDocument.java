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
	 * PlainDocument 维护无任何字符属性的普通文档。此文档的默认元素结构是文本中的行的映射。
	 * 由 getDefaultRootElement 返回的 Element 是行的映射，且每个子元素表示一行。
	 * 此模型不维护任何字符级属性，但每行都可以用一个任意的属性集来标记。
	 * 使用默认的根元素可以快速执行从行到偏移量和从偏移量到行的转换
	 */
	int maxLength = 20;

	public MyDocument(int newMaxLength) {
		super();
		maxLength = newMaxLength;
	}

	public MyDocument() {
		this(20);
	}

	// 重载父类的insertString函数
	public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {
		if (getLength() + str.length() > maxLength) {// 这里假定你的限制长度为10
			return;
		} else {
			super.insertString(offset, str, a);

		}
	}

}
