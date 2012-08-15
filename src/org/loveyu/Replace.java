package org.loveyu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

public class Replace {
	private String need = "";
	private String replace = "";
	private MyDialog subDialog;
	private JPanel p1, p11, p12, p2;
	private JTextField jt1, jt2;
	private JButton jb21, jb22, jb23, jb31, jb32, jb33;
	private CountJlable lb21;
	private ActionListener Ljb33, Ljb21, Ljb31, Ljb22, Ljb23;
	private int local = -2, lastLocal = -2;
	private boolean is_down_replace = true;
	private int lastReplace = -2;

	class CountJlable extends JLabel {
		public CountJlable() {
			// TODO Auto-generated constructor stub
			super();
			set(-1);
		}

		public void set(int n) {
			if (n > 0)
				setText("<html>向下有<font color=blue> " + n + " </font>次");
			else if (n == 0)
				setText("<html><font color=red>未找到");
			else
				setText("");
		}
	}

	public Replace() {
		// TODO Auto-generated constructor stub
		getString();
		show();
	}

	public Replace(String need) {
		this.need = need;
		show();
	}

	private String getString() {
		need = Info.text.t.getSelectedText();
		return need;
	}

	private void show() {
		InitListener();
		subDialog = new MyDialog(Info.f, "查找", false, 320, 210);
		GridLayout GL1 = new GridLayout(4, 1);
		GridLayout GL2 = new GridLayout(1, 4);
		GL1.setVgap(5);
		GL2.setHgap(5);

		p1 = new JPanel(new BorderLayout());
		p1.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
		p11 = new JPanel(GL1);
		p11.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		p12 = new JPanel(GL1);
		p2 = new JPanel(GL2);
		p2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		lb21 = new CountJlable();

		jt1 = new JTextField(need, 15);
		jt2 = new JTextField("", 15);

		jb21 = new JButton("查找一下");
		jb21.addActionListener(Ljb21);
		jb22 = new JButton("替换");
		jb22.addActionListener(Ljb22);
		jb23 = new JButton("全部替换");
		jb23.addActionListener(Ljb23);
		jb31 = new JButton("向上搜索");
		jb31.addActionListener(Ljb31);
		jb32 = new JButton("向下搜索");
		jb32.addActionListener(Ljb21);
		jb33 = new JButton("关闭");
		jb33.addActionListener(Ljb33);

		p11.add(new JLabel("查找目标："));
		p11.add(jt1);
		p11.add(new JLabel("替换为："));
		p11.add(jt2);
		p1.add(p11, BorderLayout.CENTER);

		p12.add(lb21);
		p12.add(jb21);
		p12.add(jb22);
		p12.add(jb23);
		p1.add(p12, BorderLayout.EAST);

		p2.add(jb31);
		p2.add(jb32);
		p2.add(jb33);

		subDialog.add(p1, BorderLayout.NORTH);
		subDialog.add(p2, BorderLayout.SOUTH);

		subDialog.setVisible(true);
		Message.out("display replace Dialog window");
	}

	private void InitListener() {
		Ljb33 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subDialog.close();
			}
		};
		Ljb21 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!is_down_replace) {
					lastLocal = -2;
					is_down_replace = true;
				}
				Message.out("find down");
				findAction();
			}
		};
		Ljb22 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String NewReplace = jt2.getText();
				if(NewReplace != replace){
					lastReplace = -2;
					replace = NewReplace;
				}
				if(lastReplace == local)return;
				
				if(!Info.doc.delete(local, need.length()) || !Info.doc.insert(local, replace)){
					Message.Notice("字符串替换失败", "替换提示");
					return;
				}
				ReplaceSelect();
				if(is_down_replace)
					local = local + replace.length() - 1;
				
				lastReplace = local;
			}
		};
		Ljb23 = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Message.out("Replace All");
				replace = jt2.getText();
				if(is_down_replace)ReplaceDown(local);
				else ReplaceUP(local);
				ReplaceSelect();				
			}
		};
		Ljb31 = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (is_down_replace) {
					lastLocal = -2;
					is_down_replace = false;
				}
				Message.out("find up");
				findAction();
			}
		};
	}

	private boolean findAction() {
		need = jt1.getText();
		if (!is_down_replace) {
			if (GetUpLocal() == lastLocal && local != -1) {
				Message.Notice("搜索达到顶点", "搜索提示");
				return false;
			}
		} else {
			if (GetDownLocal() == lastLocal && local != -1) {
				Message.Notice("搜索达到顶点", "搜索提示");
				return false;
			}
		}
		select();
		lastLocal = local;
		return true;
	}

	private int GetDownLocal() {
		// 向下查找

		String content = "";
		try {
			if (local >= Info.doc.styledDoc.getLength())
				local = Info.doc.styledDoc.getLength() - 1;
			if (local == -2)
				content = Info.doc.styledDoc.getText(0,
						Info.doc.styledDoc.getLength());
			else
				content = Info.doc.styledDoc.getText(local + 1,
						Info.doc.styledDoc.getLength() - local - 1);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countReplaceAction(content);
		if (local == -2)
			local = content.indexOf(need);
		else
			local = local + content.indexOf(need) + 1;

		Message.out("down find local is : " + local);
		return local;
	}

	private int GetUpLocal() {
		String content = "";
		try {
			if (local == -2)
				content = Info.doc.styledDoc.getText(0,
						Info.doc.styledDoc.getLength());
			else
				content = Info.doc.styledDoc.getText(0, local);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countReplaceAction(content);
		local = content.lastIndexOf(need);
		if (local == -1)
			local = lastLocal;
		Message.out("up find local is : " + local);
		return local;
	}

	private void select() {
		if (local >= 0)
			Info.text.t.select(local, local + need.length());
	}
	private void ReplaceSelect(){
		if (local >= 0)
			Info.text.t.select(local, local + replace.length());
	}
	private void countReplaceAction(String content) {
			int id = 0;
			int num = 0;
			while ((id = content.indexOf(need, id)) != -1) {
				num++;
				++id;
			}
			lb21.set(num);
	}
	private boolean ReplaceUP(int offset){
		String content = "";
		int allLength = Info.doc.styledDoc.getLength();
		int lastLength = allLength - offset;
		if(offset>allLength || offset < 0 )return false;
		try {
			content = Info.doc.styledDoc.getText(0, offset + need.length());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		Info.doc.delete(0, content.length());
		Info.doc.insert(0, content.replaceAll(need, replace));
		local = Info.doc.styledDoc.getLength() - lastLength - replace.length() + 1;
		return true;
	}
	private boolean ReplaceDown(int offset){
		String content = "";
		if(offset>Info.doc.styledDoc.getLength() || offset < 0 )return false;
		try {
			content = Info.doc.styledDoc.getText(offset, Info.doc.styledDoc.getLength() - offset);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		Info.doc.delete(offset, content.length());
		Info.doc.insert(offset, content.replaceAll(need, replace));
		return true;
	}
}
