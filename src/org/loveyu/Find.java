package org.loveyu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class Find {
	private String SearchWord = "";
	private ArrayList<String> result = new ArrayList<String>();
	private String Text;
	private int Count = 0;
	int NowNumber = 1;
	MyDialog subDialog;
	JPanel p1, p2, p3, p4, p42, p5;
	CountJLabel jl32;
	JTextField jt42;
	JButton jb51, jb52, jb53, jb54;
	Button b43;
	
	ActionListener Lb43, Ljb51, Ljb52, Ljb53, Ljb54;
	KeyListener Ljt42;
	
	class CountJLabel extends JLabel {

		public CountJLabel(int num) {
			// TODO Auto-generated constructor stub
			super();
			set(num);
		}

		public void set(int num) {
			setText("<html><font size=4 color=red>"
					+ num + "</font>");
		}
	}

	public Find() {
		// TODO Auto-generated constructor stub
		getString();
		Search();
	}

	private void getString() {
		String Word = "";
		Word = Info.text.t.getSelectedText();
		if (Word == null)
			Word = "";
		SearchWord = (String) JOptionPane.showInputDialog(Info.f, new JLabel(
				"输入你要查找的内容："), "查找", JOptionPane.QUESTION_MESSAGE, null, null,
				Word);
		Message.out("find word is: " + SearchWord);
	}

	private void Search() {
		if (SearchWord == null || SearchWord.equals(""))
			return;
		getList();
		Message.out("Seach result count: " + Count);
		if (Count <= 0)
			Message.Notice("<html>没有搜索到：<font size=5>\"<font color=green>"
					+ SearchWord + "</font>\"</font>", "搜索结果");
		else {
			InitListener();
			show();
		}

	}

	private void getList() {
		try {
			Text = Info.doc.styledDoc.getText(0, Info.doc.styledDoc.getLength());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id = 0;
		while ((id = Text.indexOf(SearchWord, id)) != -1) {
			result.add(String.valueOf(id));
			++id;
		}
		Count = result.size();
	}

	private void show() {
		setSelect();// 选中当前文字
		subDialog = new MyDialog(Info.f, "查询结果", true, 350, 200);
		GridLayout GL1 = new GridLayout(1,4);
		GL1.setHgap(5);
		GridLayout GL2 = new GridLayout(5,1);
		FlowLayout FL = new FlowLayout();
		FL.setAlignment(FlowLayout.LEFT);
		
		p1 = new JPanel(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		p2 = new JPanel(new BorderLayout());
		p2.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		p3 = new JPanel(new BorderLayout());
		p3.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		p4 = new JPanel(FL);
		p4.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		p5 = new JPanel(GL1);
		p5.setBorder(BorderFactory.createEmptyBorder(0,5,10,5));
		
		p1.add(new JLabel("<html><font size=4>关键字：</font>"),BorderLayout.WEST);
		p1.add(new JLabel("<html><font size=5 color=red>" + SearchWord + "</font>"),BorderLayout.CENTER);
		
		p2.add(new JLabel("<html><font size=3>查找统计：</font>"),BorderLayout.WEST);
		p2.add(new JLabel("<html><font size=4 color=blue>"
						+ Count + "</font>"),BorderLayout.CENTER);
		p3.add(new JLabel("<html><font size=3>当前位置："),BorderLayout.WEST);		
		jl32 = new CountJLabel(NowNumber);
		p3.add(jl32,BorderLayout.CENTER);
		
		p4.add(new JLabel("<html><font size=4>跳转到：</font>"),FlowLayout.LEFT);
		jt42 = new JTextField(String.valueOf(((NowNumber >= Count) ? Count
				: NowNumber + 1)), 10);
		jt42.addKeyListener(Ljt42);
		p4.add(jt42,FlowLayout.CENTER);
		b43 = new Button("确定");
		b43.addActionListener(Lb43);
		p4.add(b43,FlowLayout.RIGHT);
		
		jb51 = new JButton("下一处");
		jb51.addActionListener(Ljb51);
		jb52 = new JButton("上一处");
		jb52.addActionListener(Ljb52);
		jb53 = new JButton("末尾");
		jb53.addActionListener(Ljb53);
		jb54 = new JButton("关闭");
		jb54.addActionListener(Ljb54);
		p5.add(jb51);
		p5.add(jb52);
		p5.add(jb53);
		p5.add(jb54);
		
		subDialog.setLayout(GL2);
		subDialog.add(p1);
		subDialog.add(p2);
		subDialog.add(p3);
		subDialog.add(p4);
		subDialog.add(p5);	
		subDialog.addKeyListener(Ljt42);
		
		subDialog.setVisible(true);
	}

	private void setSelect() {
		if (NowNumber <= 0){
			NowNumber = 1;
			Message.Notice("搜索达到顶端", "搜索提示");
		}else if (NowNumber > Count){
			NowNumber = Count;
			Message.Notice("搜索达到最底端", "搜索提示");
		}
		Info.text.t.select(
				Integer.parseInt(result.get(NowNumber - 1)),
				Integer.parseInt(result.get(NowNumber - 1))
						+ SearchWord.length());
	}

	private void InitListener() {
		Ljt42 = new KeyListener() {
			//监听回车键
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar()==KeyEvent.VK_ENTER){
					NowNumber = TextChange();
					setSelect();
					DialogChange();					
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		Lb43 = new ActionListener() {
			// 输入框确定按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NowNumber = TextChange();
				setSelect();
				DialogChange();
			}
		};

		Ljb51 = new ActionListener() {
			// 下一处
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NowNumber++;
				setSelect();
				DialogChange();
			}
		};
		Ljb52 = new ActionListener() {
			// 上一处
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NowNumber--;
				setSelect();
				DialogChange();
			}
		};
		Ljb53 = new ActionListener() {
			// 末尾
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NowNumber = Count;
				setSelect();
				DialogChange();
			}
		};
		Ljb54 = new ActionListener() {
			// 关闭按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subDialog.close();
			}
		};

	}

	private int TextChange() {
		int s = 0;
		try {
			s = Integer.parseInt(jt42.getText());
		} catch (Exception e2) {
			// TODO: handle exception
			s = NowNumber;
		}

		if (s <= 0) {
			jt42.setText("1");
			s = 1;
		} else if (s > Count) {
			jt42.setText(String.valueOf(Count));
			s = Count;
		}
		return s;
	}

	private void DialogChange() {
		if (NowNumber <= 0)
			NowNumber = 1;
		else if (NowNumber > Count)
			NowNumber = Count;

		jt42.setText(String.valueOf((NowNumber >= Count) ? Count : NowNumber + 1));
		jl32.set(NowNumber);
	}
}
