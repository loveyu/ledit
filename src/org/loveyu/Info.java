package org.loveyu;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

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
			System.out.println("window close action");
			Info.CloseAction();
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			try {
				System.out.println("windows had closed !");
					Thread.sleep ( 1000 ) ; 
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://www.loveyu.org/?fapp=LEdit");
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
		MyDialog dlg=new MyDialog(f,"你确定要关闭程序吗？");
		dlg.setVisible(true);
		if(dlg.getStatus()=="YES"){
			f.dispose();
			System.out.println("Exit");
			//System.exit(0);
			return true;
		}else{
			System.out.println("give up exit!");
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