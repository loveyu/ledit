package org.loveyu;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Message extends JOptionPane{
	static int YES_NO(String message,String title,String HtmlColor){
		if(HtmlColor==null)HtmlColor="balck";
		return showConfirmDialog(Info.f,new JLabel("<html><font size=5 color=" + HtmlColor + ">" + message),title,YES_NO_OPTION);
	}
	static void Notice(String message,String title){
		JOptionPane.showMessageDialog(Info.f,new JLabel(message),title,INFORMATION_MESSAGE);
	}
	static void out(String s){
		System.out.println(s);
	}
	static boolean ChoseFile(String list[]){
		int i;
		String name;
		Button bu;
		JPanel jp = new JPanel(new GridLayout(0,2,1,1));
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = ((Button)e.getSource()).getName();
				Info.DndID = Integer.parseInt(name);
				out("chose id is " + Info.DndID);
			}
		};
		for(i=0; i<list.length; ++i){
			name = list[i].substring(list[i].lastIndexOf("\\")+1);
			if(name.equals(list[i]))name = list[i].substring(list[i].lastIndexOf("/")+1);
			out("out file name : " + name);
			bu = new Button(name);
			bu.setName(Integer.toString(i));
			bu.addActionListener(listener);
			jp.add(bu);
		}
		int status = showConfirmDialog(Info.f,jp,"选择你要打开的文件",OK_CANCEL_OPTION,PLAIN_MESSAGE);
		if(status == CANCEL_OPTION)return false;
		return true;
	}
}
