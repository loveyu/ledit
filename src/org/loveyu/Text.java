package org.loveyu;


import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.*;


public class Text extends JFrame{
	
	public JTextPane t;
	public Text() {
		Info.doc = new Doc();
		t = new JTextPane(Info.doc.Init());
		t.setDropTarget( new DropTarget(this, DnDConstants.ACTION_REFERENCE, new DndTargetListener(), true) );
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
	public String getText(){
		return t.getText();
	}
}
