package org.loveyu;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Info{
	final static String app_title="LEdit";
	static String init_content="";
	static InitShow f;
	static String file_title=null;
	static String path=null;
	static String dir=null;
	static Doc doc;
	static Text text;
	static boolean emptyDoc=true;
	
	final static boolean closeOpenUIL = false;//关闭后打开网址
	final static boolean closeNotice = false;//关闭提示
	
	static String[] DndFileList;
	static int DndID = -1;
	static WindowListener WindowListener = new WindowListener() {

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			Message.out("window close action");
			Info.CloseAction();
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			try {
				Message.out("windows had closed !");
					Thread.sleep ( 1000 ) ; 
				if(closeOpenUIL)Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.loveyu.org/?fapp=LEdit");
				System.exit(0);
			} catch (IOException | InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	static boolean CloseAction(){
		FileAction fa=new FileAction(Info.f);
		fa.CloseFile(false);
		/*
		MyDialog dlg=new MyDialog(f,"你确定要关闭程序吗？");
		dlg.setVisible(true);	
		*/
		if(closeNotice==false || Message.YES_NO("确定退出编辑器？","退出程序？","red")==Message.OK_OPTION){
			f.dispose();
			Message.out("Exit action");
			//System.exit(0);
			return true;
		}else{
			Message.out("give up exit!");
			return false;
		}
	}
	static boolean ClearFileInfo(){
		init_content="";
		path=null;
		file_title=null;
		text.SetEmpty();
		f.setTitle(null);
		return true;
	}
}