package org.loveyu;

import java.awt.datatransfer.DataFlavor;
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
		if(!dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){
			dtde.rejectDrag();
		}
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	
}
