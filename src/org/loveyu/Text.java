package org.loveyu;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class Text extends JFrame{
	
	public JTextPane t;
	InitShow f;
	public Text(InitShow f) {
		this.f=f;
		Info.doc = new Doc();
		t = new JTextPane(Info.doc.Init());
		Info.doc.SetDefaultStyle();
		Info.text = this;
		Info.doc.SetContent(Info.init_content);		
	}	 
	JScrollPane showText(){
		return new JScrollPane(t);
	}
	
	public void SetEmpty(){
		t.setText("");
	}
	public void setTextContent(String s){
		Info.doc.SetContent(s);
	}
	
}
