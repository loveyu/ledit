package org.loveyu;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MyDialog extends Dialog {

	public MyDialog(Frame f, String title, boolean mod, int width, int height) {
		super(f, title, mod);
		setLocation((int) (f.getX() + (f.getWidth() - width) / 1.62),
				(int) (f.getY() + (f.getHeight() - height) / 2.62));
		setSize(width, height);
		setResizable(false);//禁止调整大小
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		// TODO Auto-generated constructor stub
	}
	public void close(){
		dispose();
	}
}
