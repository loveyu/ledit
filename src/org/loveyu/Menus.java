package org.loveyu;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class Menus{
	private JMenu[] menus={
			new JMenu("文件"),
			new JMenu("编辑")
	};
	private JMenuItem[] items={
			new JMenuItem("打开",KeyEvent.VK_O),
			new JMenuItem("新建",KeyEvent.VK_N),
			new JMenuItem("保存",KeyEvent.VK_S),
			new JMenuItem("另存"),
			new JMenuItem("关闭",KeyEvent.VK_C),
			new JMenuItem("退出",KeyEvent.VK_X),
			
			new JMenuItem("清空")
	};
	InitShow f;
	
	private ActionListener menus_Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = ((JMenuItem)e.getSource()).getName();
			FileAction fa=new FileAction(Info.f);
			switch(name){
				case "Open":
					System.out.println("file open action");
					String filePath=fa.getFile();
					if(filePath!=null){
						System.out.println("succussful chose file "+filePath);
						
						if(fa.ReadFile()){
							System.out.println("Open file ok");
						} 
						
					}
					
				break;
				case "New":
					fa.CloseFile(true);
					f.setTitle("新文件  ");
					
					System.out.println("new file action");
				break;
				case "Save":
					System.out.println("save file action");
					if(fa.saveFile())System.out.println("save file succussful");
					else System.out.println("save file error");				
				break;
				
				case "SaveOther":
					System.out.println("save other file action");
					String tmp=Info.path;
					Info.path=null;
					if(fa.saveFile())System.out.println("save other file succussful");
					else{
						System.out.println("save other file error\nSet old file path");
						Info.path=tmp;
					}
				break;				
				
				case "Close":
					
					fa.CloseFile(true);
					System.out.println("close now file action");
				break;				
				case "Exit":
					System.out.println("Menus Exit action");
					Info.CloseAction();				
				break;
				
				case "Clear":
					System.out.println("clear Text conent");
					f.text.t.setText("");
				break;
			}
		}
	};
	public Menus(InitShow f) {
		this.f=f;
		// TODO Auto-generated constructor stub
		menus[0].add(items[0]);
		items[0].setName("Open");
		items[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
		
		menus[0].add(items[1]);
		items[1].setName("New");
		items[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,Event.CTRL_MASK));
		
		menus[0].add(items[2]);
		items[2].setName("Save");
		items[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		
		menus[0].add(items[3]);
		items[3].setName("SaveOther");		
		
		menus[0].add(items[4]);
		items[4].setName("Close");
		items[4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.ALT_MASK));
		
		menus[0].add(items[5]);
		items[5].setName("Exit");		
		items[5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Event.ALT_MASK));
		
		menus[1].add(items[6]);
		items[6].setName("Clear");
		
		menus[0].setName("File");
		menus[1].setName("Edit");
	}
	public JMenuBar getMenus() {
		for(JMenuItem JI:items)JI.addActionListener(menus_Listener);
		JMenuBar mb = new JMenuBar();
		
		mb.add(menus[0]);
		mb.add(menus[1]);
		return (mb);
	}
}
