package Initialization;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class Text extends JFrame{
	public JTextPane t = new JTextPane();
	InitShow f;
	private CaretListener listener = new CaretListener() {
		
		@Override
		public void caretUpdate(CaretEvent e) {
			// TODO Auto-generated method stub
			System.out.println("text action ");
			if((f.getTitle().substring(0, 3)).equals("ÒÑ±£´æ")){
				f.setTitle(Info.file_title);
				System.out.println("123");
			}

		}
	};
	public Text(InitShow f) {
		this.f=f;
		t.setText(Info.init_content);
		t.addCaretListener(listener);
		
	}
	JScrollPane showText(){
		return new JScrollPane(t);
	}
}
