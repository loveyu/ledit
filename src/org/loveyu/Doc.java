package org.loveyu;

import java.awt.Color;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

public class Doc {
	private StyleContext context = new StyleContext();// 实例化一个样式池
	public StyledDocument styledDoc;
	private Style style;
	private DocumentListener listener = new DocumentListener() {

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			Message.out("removeUpdate Action");
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			Message.out("insertUpdate Action");
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			Message.out("changedUpdate Action");
		}
	};

	public StyledDocument Init() {
		styledDoc = new DefaultStyledDocument(context);// 创建有样式的文档实例
		style = context.getStyle(StyleContext.DEFAULT_STYLE);// 从样式池获取默认的样式
		styledDoc.addDocumentListener(listener);

		return styledDoc;
	}

	public void SetDefaultStyle() {
		createStyle(14, "", Color.darkGray, false, false, false);
	}

	public void SetContent(String v) {
		insertDoc(styledDoc, v);
	}

	public void createStyle(int size, String fontName, Color color,
			boolean bold, boolean italic, boolean underline) {
		StyleConstants.setFontSize(style, size);// 设置字体大小
		StyleConstants.setFontFamily(style, fontName);// 设置字体
		StyleConstants.setForeground(style, color);// 设置颜色
		StyleConstants.setBold(style, bold);// 加粗
		StyleConstants.setItalic(style, italic);// 斜体
		StyleConstants.setUnderline(style, underline);// 下划线
	}

	public boolean insert(int offset, String str) {
		Message.out("doc insert action -> offset: " + offset + " str: " + str);
		if (offset > styledDoc.getLength() || offset < 0)
			return false;
		try {
			styledDoc.insertString(offset, str, style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(int offset, int length) {
		Message.out("doc delete action");
		if (offset < 0 || offset + length > styledDoc.getLength())
			return false;
		try {
			styledDoc.remove(offset, length);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void insertDoc(StyledDocument styledDoc, String content) {
		try {
			styledDoc.insertString(styledDoc.getLength(), content, style);
		} catch (BadLocationException e) {
			System.err.println("BadLocationException: " + e);
		}
	}
}
