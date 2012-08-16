package org.loveyu;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LEdit extends SwingConsole {
	public LEdit() {
		run(new InitShow(), (int) (600 * 1.62), 600);
	}
}

class InitShow extends JFrame {
	Menus menus;
	Text text;
	JPanel jp0, jp1;
	JLabel jl;

	public void setTitle(String title) {
		if (title != null)
			super.setTitle(title + " - " + Info.app_title);
		else
			super.setTitle(Info.app_title);
	}

	public InitShow() {
		Info.f = this;
		menus = new Menus();
		text = new Text();
		jp0 = new JPanel(new BorderLayout());
		jp1 = new JPanel(new BorderLayout());
		setJMenuBar(menus.getMenus());// ²Ëµ¥
		jp1.add(text.showText(), BorderLayout.CENTER);		
		jp0.add(jp1, BorderLayout.CENTER);		
		add(jp0);// ÎÄ±¾¿ò
		Message.out("app had initlaztion");
	}

}
