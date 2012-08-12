package org.loveyu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyDialog extends JDialog{
	private int width=150,height=100;
	private String status=null;
	private ActionListener listener=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(((JButton)e.getSource()).getText().equals("ÊÇ/Y"))status="YES";
			else status="NO";
			dispose();
		}
	};
	KeyListener key_l=new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			switch(e.getKeyChar()){
				case 'Y':
				case 'y':
					status="YES";
					dispose();
					break;
				case 'N':
				case 'n':
					status="NO";
					dispose();
					break;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	public String getStatus(){
		return status;
	}
	public MyDialog(InitShow parent,String text) {
		// TODO Auto-generated constructor stub
		super(parent,text,true);
		setLocation((int)(parent.getX()+(parent.getWidth()-width)/2.62), (int)(parent.getY()+(parent.getHeight()-height)/2.62));
		JButton yes=new JButton("ÊÇ/Y");
		JButton no=new JButton("·ñ/N");
		JLabel lable=new JLabel(text);
		yes.addActionListener(listener);
		yes.addKeyListener(key_l);
		no.addActionListener(listener);
		setLayout(new FlowLayout());
		add(lable);
		add(yes);
		add(no);
		setSize(width,height);
	}
}
