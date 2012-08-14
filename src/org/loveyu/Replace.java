package org.loveyu;

public class Replace {
	String need = "";
	String replace = null;
	MyDialog subDialog;
	public Replace() {
		// TODO Auto-generated constructor stub
		getString();
		show();
	}
	public Replace(String need){
		this.need = need;
		show();
	}
	private String getString(){
		need = Info.text.t.getSelectedText();
		return need;
	}
	private void show(){
		subDialog  = new MyDialog(Info.f, "≤È’“", false, 300, 200);
		
		subDialog.setVisible(true);
		Message.out("display replace Dialog window");
	}
}
