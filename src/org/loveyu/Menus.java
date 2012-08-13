package org.loveyu;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class Menus{
	private JMenu[] menus={
			new JMenu("文件"),
			new JMenu("编辑"),
			new JMenu("关于")
	};
	private JMenuItem[][] items={
			{
				new JMenuItem("打开",KeyEvent.VK_O),
				new JMenuItem("新建",KeyEvent.VK_N),
				new JMenuItem("保存",KeyEvent.VK_S),
				new JMenuItem("另存"),
				new JMenuItem("关闭",KeyEvent.VK_C),
				new JMenuItem("退出",KeyEvent.VK_X),
			},
			
			{
				new JMenuItem("清空"),
				new JMenuItem("查找"),
			},
			{
				new JMenuItem("作者"),
				new JMenuItem("程序")
			}
	};
	InitShow f;
	
	private ActionListener menus_Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = ((JMenuItem)e.getSource()).getName();
			FileAction fa=new FileAction(Info.f);
			switch(name){
			
				/* 文件操作开始  */
				case "Open":
					Message.out("file open action");
					String filePath=fa.getFile();
					if(filePath!=null){
						Message.out("succussful chose file "+filePath);
						
						if(fa.ReadFile()){
							Message.out("Open file ok");
						} 
						
					}
				break;
				case "New":
					fa.CloseFile(true);
					f.setTitle("新文件  ");
					
					Message.out("new file action");
				break;
				case "Save":
					Message.out("save file action");
					if(fa.saveFile())Message.out("save file succussful");
					else Message.out("save file error");				
				break;
				
				case "SaveOther":
					Message.out("save other file action");
					String tmp=Info.path;
					Info.path=null;
					if(fa.saveFile())Message.out("save other file succussful");
					else{
						Message.out("save other file error\nSet old file path");
						Info.path=tmp;
					}
				break;				
				
				case "Close":
					
					fa.CloseFile(true);
					Message.out("close now file action");
				break;				
				case "Exit":
					Message.out("Menus Exit action");
					Info.CloseAction();				
				break;
				/* 文件操作 结束 */
				
				/* 编辑操作开始 */
				case "Clear":
					Message.out("clear Text conent");
					f.text.t.setText("");
				break;
				
				case "Find":
					Message.out("Find text");
					new Find();
				break;
				/* 编辑操作结束 */
				
				/* 关于操作开始 */
				case "Author":
					Message.Notice(("<html><font size=5>"+
            "<center><b>LEdit Java 文本编辑器</b</center></font>" +
			"<font size=4><br><left>作者：胡志宇" +
            "<br>博客：http://www.loveyu.org</left></font>"
			),"关于作者");
				break;
				
				case "Program":
					Message.Notice("<html><font size=5>"+
				            "<center><b>LEdit Java 文本编辑器</b</center></font>" +
							"<font size=4><br><left>程序版本：0.1" +
				            "<br>网址：https://github.com/loveyu/ledit</left></font>", "关于");
				break;
				/* 关于操作结束 */
				
			}
		}
	};
	public Menus(InitShow f) {
		this.f=f;
		// TODO Auto-generated constructor stub
		
		//number 0
		menus[0].add(items[0][0]);
		items[0][0].setName("Open");
		items[0][0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,Event.CTRL_MASK));
		
		menus[0].add(items[0][1]);
		items[0][1].setName("New");
		items[0][1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,Event.CTRL_MASK));
		
		menus[0].add(items[0][2]);
		items[0][2].setName("Save");
		items[0][2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,Event.CTRL_MASK));
		
		menus[0].add(items[0][3]);
		items[0][3].setName("SaveOther");		
		
		menus[0].add(items[0][4]);
		items[0][4].setName("Close");
		items[0][4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.ALT_MASK));
		
		menus[0].add(items[0][5]);
		items[0][5].setName("Exit");		
		items[0][5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,Event.ALT_MASK));
		
		// number 1
		menus[1].add(items[1][0]);
		items[1][0].setName("Clear");
		
		menus[1].add(items[1][1]);
		items[1][1].setName("Find");
		items[1][1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,Event.CTRL_MASK));
		
		// number 2
		menus[2].add(items[2][0]);
		items[2][0].setName("Author");
		
		menus[2].add(items[2][1]);
		items[2][1].setName("Program");
		
		// number all
		menus[0].setName("File");
		menus[1].setName("Edit");
		menus[2].setName("About");
	}
	public JMenuBar getMenus() {
		for(JMenuItem[] JI:items)for(JMenuItem JI2:JI)JI2.addActionListener(menus_Listener);
		JMenuBar mb = new JMenuBar();
		
		for(JMenu ME:menus)mb.add(ME);

		return (mb);
	}
}
