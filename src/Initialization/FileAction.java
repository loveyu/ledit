package Initialization;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class FileAction{
	InitShow f;
	String path;
	File file;
	JFileChooser chooser = new JFileChooser();
	int returnVal;
	public FileAction(InitShow parent) {
		// TODO Auto-generated constructor stub
		f=parent;
	}
	public String getFile(){
		CloseFile();
	    chooser.setFileFilter(new FileNameExtensionFilter(
		        "Text type file", "txt","js","html","html","php","css","bat","java","xml"));
	    if(Info.dir!=null)chooser.setCurrentDirectory(new File(Info.dir));
	    returnVal = chooser.showOpenDialog(f);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       path=chooser.getSelectedFile().getPath();
	       System.out.println("You chose to open this file: " + path);
	       Info.path=path;
	       Info.dir=chooser.getSelectedFile().getParent();
	       return path;
	    }else{
	    	System.out.println("file chose action had cancel");
	    }
	    return null;
	}
	public boolean saveFile(){
		if(Info.path==null){
			do{	
				if(Info.dir!=null)chooser.setCurrentDirectory(new File(Info.dir));
				returnVal=chooser.showSaveDialog(f);
				if(JFileChooser.APPROVE_OPTION!=returnVal)
					break;
				Info.dir=chooser.getSelectedFile().getParent();
				if( chooser.getSelectedFile().isFile()){
					System.out.println("文件已存在  :"+ chooser.getSelectedFile().getPath());
					MyDialog dlg=new MyDialog(f,"你确定要覆盖文件吗？");
					dlg.setVisible(true);
					if(dlg.getStatus()=="YES"){
						System.out.println("chose Yes");
						break;
					}else{
						System.out.println("chose NO");
						continue;
					}
				}else break;
			}while(JFileChooser.CANCEL_OPTION!=returnVal);
			
			if(returnVal==JFileChooser.APPROVE_OPTION){
				System.out.println("save file to:"+chooser.getSelectedFile().getPath());
				Info.path=chooser.getSelectedFile().getPath();
				Info.file_title=chooser.getSelectedFile().getName();
				return saveFile();
			}
			System.out.println("give up save file");
			return false;
		}else{
			System.out.println("begin write file :"+Info.path);
			
			PrintWriter out;
			try {
				out = new PrintWriter(new File(Info.path).getAbsoluteFile());
				out.print(Info.f.text.t.getText());
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			f.setTitle("已保存"+" - "+Info.file_title);
			Info.init_content=Info.f.text.t.getText();
			System.out.println("succuffuly save file");
		}
		return true;
	}
	public boolean ReadFile(){
		file=new File(path);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			
			try {
				String s;
				Info.init_content="";
				while((s=in.readLine())!=null){
					Info.init_content+=s+"\n";
				}
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		//System.out.println(Info.init_content);
		f.text.t.setText(Info.init_content);
		f.setTitle(file.getName());
		Info.file_title=file.getName();
		return true;
	}
	public boolean CloseFile(){
		if(!Info.f.text.t.getText().equals(Info.init_content)){
			System.out.println("file is no save");
			MyDialog dlg=new MyDialog(f,"是否保存文件?");
			dlg.setVisible(true);
			if(dlg.getStatus()=="YES"){
				System.out.println("chose Yes\nsave file");
				saveFile();
			}else{
				System.out.println("chose NO\ngive up save file");
			}
		}
		Info.ClearFileInfo();
		return true;
	}
}
