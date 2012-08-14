package org.loveyu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Find {
	private String SearchWord = "";
	private ArrayList<String> result = new ArrayList<String>();
	private String Text;
	private int Count = 0;
	int NowNumber = 1;
	MyDialog subDialog;
	Panel p1, p2, p3, p4;
	JLabel jl1, jl3;
	CountJLabel jl2;
	JTextField t1;
	JButton jb1, jb2, jb3, jb4;
	Button b1;
	ActionListener Lb1, Ljb3, Ljb1, Ljb2, Ljb4;

	class CountJLabel extends JLabel {

		public CountJLabel(int num) {
			// TODO Auto-generated constructor stub
			super();
			set(num);
		}

		public void set(int num) {
			setText("<html><br><font size=3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;当前第 <font size=4 color=red>"
					+ num + "</font> 处</font><br>");
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
		Text = Info.text.getText();
		long id = 0;
		while ((id = Text.indexOf(SearchWord, (int) id)) != -1) {
			result.add(String.valueOf(id));
			++id;
		}
		Count = result.size();
	}

	private void show() {
		setSelect();// 选中当前文字
		subDialog = new MyDialog(Info.f, "查询结果", true, 320, 240);
		p1 = new Panel(new BorderLayout());
		p2 = new Panel(new GridLayout(1, 4));
		p3 = new Panel(new FlowLayout());
		p4 = new Panel(new BorderLayout());

		jl1 = new JLabel(
				"<html><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size=4>关键字：</font><font size=5 color=red>"
						+ SearchWord
						+ "</font><br><br><font size=3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共查询到   <font size=4 color=blue>"
						+ Count + "</font> 处</font>");
		jl2 = new CountJLabel(NowNumber);

		jl3 = new JLabel(
				"<html><font size=4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;跳转到：</font>");

		t1 = new JTextField(String.valueOf(((NowNumber >= Count) ? Count
				: NowNumber + 1)), 10);

		jb1 = new JButton("下一处");
		jb1.addActionListener(Ljb1);

		jb2 = new JButton("上一处");
		jb2.addActionListener(Ljb2);

		jb3 = new JButton("关闭");
		jb3.addActionListener(Ljb3);

		jb4 = new JButton("末尾");
		jb4.addActionListener(Ljb4);

		b1 = new Button("确定");
		b1.addActionListener(Lb1);

		p1.add(jl1, BorderLayout.NORTH);
		p1.add(jl2, BorderLayout.SOUTH);

		p2.add(jb1);
		p2.add(jb2);
		p2.add(jb4);
		p2.add(jb3);

		p3.add(jl3);
		p3.add(t1);
		p3.add(b1);

		p4.add(p3, BorderLayout.NORTH);
		p4.add(new JLabel("<html><br>"), BorderLayout.CENTER);
		p4.add(p2, BorderLayout.SOUTH);

		subDialog.add(p1, BorderLayout.NORTH);
		subDialog.add(p4, BorderLayout.SOUTH);
		subDialog.setVisible(true);
	}

	private void setSelect() {
		if (NowNumber <= 0)
			NowNumber = 1;
		else if (NowNumber > Count)
			NowNumber = Count;
		Info.text.t.select(
				Integer.parseInt(result.get(NowNumber - 1)),
				Integer.parseInt(result.get(NowNumber - 1))
						+ SearchWord.length());
	}

	private void InitListener() {
		Lb1 = new ActionListener() {
			// 输入框确定按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NowNumber = TextChange();
				setSelect();
				DialogChange();
			}
		};
		Ljb1 = new ActionListener() {
			// 下一处
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NowNumber++;
				setSelect();
				DialogChange();
			}
		};
		Ljb2 = new ActionListener() {
			// 上一处
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NowNumber--;
				setSelect();
				DialogChange();
			}
		};
		Ljb3 = new ActionListener() {
			// 关闭按钮
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subDialog.close();
			}
		};
		Ljb4 = new ActionListener() {
			// 末尾
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NowNumber = Count;
				setSelect();
				DialogChange();
			}
		};

	}

	private int TextChange() {
		int s = 0;
		try {
			s = Integer.parseInt(t1.getText());
		} catch (Exception e2) {
			// TODO: handle exception
			s = NowNumber;
		}

		if (s <= 0) {
			t1.setText("1");
			s = 1;
		} else if (s > Count) {
			t1.setText(String.valueOf(Count));
			s = Count;
		}
		return s;
	}

	private void DialogChange() {
		if (NowNumber <= 0)
			NowNumber = 1;
		else if (NowNumber > Count)
			NowNumber = Count;

		t1.setText(String.valueOf((NowNumber >= Count) ? Count : NowNumber + 1));
		jl2.set(NowNumber);
	}
}
