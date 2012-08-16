package org.loveyu;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DndTargetListener implements DropTargetListener{

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		Message.out("start dnd action");
		DataFlavor[] dataFlavors = dtde.getCurrentDataFlavors();
        if(dataFlavors[0].match(DataFlavor.javaFileListFlavor)){
            try {
                Transferable tr = dtde.getTransferable();
                Object obj = tr.getTransferData(DataFlavor.javaFileListFlavor);
				List<File> files = (List<File>)obj;
                Info.DndFileList = new String[files.size()];
                for(int i = 0; i < files.size(); i++){
                    Message.out("open file : " + files.get(i).getAbsolutePath());
                    Info.DndFileList[i] = files.get(i).getAbsolutePath();
                }
            } catch (UnsupportedFlavorException ex) {

            } catch (IOException ex) {

            }
        }
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		//Message.out("dnd in action");
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		Message.out("user change action");
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		Message.out("give up dnd action");
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		String FilePath=null;
		if(Info.DndFileList.length<=0){
			Message.Notice("拖放打开文件错误！","打开错误");
			return;
		}else if(Info.DndFileList.length>1){
			Message.out("chose " + Info.DndFileList.length + " file");
			if(!Message.ChoseFile(Info.DndFileList) || Info.DndID==-1){
				Message.out("cancal dnd open file");
				return;
			} 
			FilePath = Info.DndFileList[Info.DndID];
			Info.DndID = -1;//恢复-1值
		}else FilePath = Info.DndFileList[0];
		Message.out("success dnd action");
		//打开第一个文件
		FileAction fa = new FileAction(Info.f);
		fa.CloseFile(true);
		fa.path = FilePath;
		if(!fa.ReadFile()){
			Message.Notice("拖放打开文件错误！","打开错误");
		}
		Message.out("fileOpen");
	}
	
}
