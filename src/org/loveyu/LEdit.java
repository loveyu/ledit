package org.loveyu;

import javax.swing.*;

public class LEdit extends SwingConsole {
	public LEdit() {
		run(new InitShow(), (int)(600*1.62), 600);
	}
}

class InitShow extends JFrame {
	Menus menus;
	Text text;

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

		setJMenuBar(menus.getMenus());// ²Ëµ¥
		add(text.showText());// ÎÄ±¾¿ò

		Message.out("app had initlaztion");
	}

}
