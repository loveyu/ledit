package org.loveyu;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;

public class Text extends JFrame {

	public JTextPane t;
	public JScrollPane Js;
	private UndoManager undomgr;

	public Text() {
		Info.doc = new Doc();
		t = new JTextPane(Info.doc.Init());
		t.setDropTarget(new DropTarget(this, DnDConstants.ACTION_REFERENCE,
				new DndTargetListener(), true));
		Info.doc.SetDefaultStyle();
		Info.text = this;
		Info.doc.SetContent(Info.init_content);
		AddUndoManager();
	}

	JScrollPane showText() {
		Js = new JScrollPane(t);
		return Js;
	}

	public void SetEmpty() {
		t.setText("");
	}

	public void setTextContent(String s) {
		Info.doc.SetContent(s);
	}

	public String getText() {
		return t.getText();
	}

	private void AddUndoManager() {
		// 增加撤销操作
		undomgr = new UndoManager() {
			public void undoableEditHappened(UndoableEditEvent e) {
				this.addEdit(e.getEdit());
			}
		};
		t.getDocument().addUndoableEditListener(undomgr);
	}

	void undo() {
		if (undomgr.canUndo())
			undomgr.undo();
	}

	void redo() {
		if (undomgr.canRedo())
			undomgr.redo();
	}

}
