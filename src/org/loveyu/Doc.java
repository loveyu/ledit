package org.loveyu;

import java.awt.Color;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

public class Doc {
	private StyleContext context = new StyleContext();// ʵ����һ����ʽ��
	private StyledDocument styledDoc;
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
			if (Info.emptyDoc) {
				Info.emptyDoc = false;
			}
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
		createStyle(18, "", Color.darkGray, false, false, false);
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

	public void insertDoc(StyledDocument styledDoc, String content) {
		try {
			styledDoc.insertString(styledDoc.getLength(), content, style);
		} catch (BadLocationException e) {
			System.err.println("BadLocationException: " + e);
		}
	}
}
