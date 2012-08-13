package org.loveyu;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

import javax.swing.*;

public class LEdit  extends SwingConsole{
	public LEdit() {
		run(new InitShow(),800,500);
	}	
}

class InitShow extends JFrame{
	Menus menus=new Menus(this);
	Text text=new Text(this);
	public void setTitle(String title){
		if(title!=null)super.setTitle(title+" - "+Info.app_title);
		else super.setTitle(Info.app_title);
	}
	public InitShow() {
		Info.f=this;
		
		setJMenuBar(menus.getMenus());//²Ëµ¥
		add(text.showText());//ÎÄ±¾¿ò
		
		Message.out("app had initlaztion");
	}

}
