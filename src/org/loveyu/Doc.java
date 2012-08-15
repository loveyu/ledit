package org.loveyu;

import java.awt.Color;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

public class Doc {
	private StyleContext context = new StyleContext();// ʵ����һ����ʽ��
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
		styledDoc = new DefaultStyledDocument(context);// ��������ʽ���ĵ�ʵ��
		style = context.getStyle(StyleContext.DEFAULT_STYLE);// ����ʽ�ػ�ȡĬ�ϵ���ʽ
		styledDoc.addDocumentListener(listener);
		
		return styledDoc;
	}

	public void SetDefaultStyle() {
		createStyle(14, "", Color.darkGray, false, false, false);
	}

	public void SetContent(String v) {
		insertDoc(styledDoc, v);
	}

	public void createStyle(int size, String fontName, Color color, boolean bold, boolean italic, boolean underline) {
		StyleConstants.setFontSize(style, size);// ���������С
		StyleConstants.setFontFamily(style, fontName);// ��������
		StyleConstants.setForeground(style, color);// ������ɫ
		StyleConstants.setBold(style, bold);// �Ӵ�
		StyleConstants.setItalic(style, italic);// б��
		StyleConstants.setUnderline(style, underline);// �»���
	}
	public boolean insert(int offset, String str){
		Message.out("doc insert action -> offset: " + offset + " str: " + str);
		if(offset > styledDoc.getLength() || offset < 0) return false;
		try {
			styledDoc.insertString(offset, str, style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean delete(int offset, int length){
		Message.out("doc delete action");
		if(offset < 0 || offset + length > styledDoc.getLength())return false;
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
