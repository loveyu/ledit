package Initialization;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;

import javax.swing.*;

public class Init  extends SwingConsole{
	public Init() {
		run(new InitShow(),800,500);
	}
	public Boolean Status() {
		return true;
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
		//�˵�
		Info.f=this;
		setJMenuBar(menus.getMenus());
		text.t.setDropTarget( new DropTarget(this, DnDConstants.ACTION_REFERENCE, new DndTargetListener(), true) );
		add(text.showText());
		System.out.println("app had initlaztion");
	}

}