package org.loveyu;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Find {
	private String SearchWord = "";
	private ArrayList<String> result = new ArrayList<String>();
	private String Text;
	private int Count = 0;

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
		SearchWord = (String) JOptionPane.showInputDialog(Info.f,
					new JLabel("输入你要查找的内容："), "查找",
					JOptionPane.QUESTION_MESSAGE, null, null, Word);
		Message.out("find word is: " + SearchWord);
	}

	private void Search() {
		if (SearchWord==null || SearchWord.equals(""))
			return;
		getList();
		Message.out("Seach result count: " + Count);
		if(Count <= 0 )Message.Notice("<html>没有搜索到：<font size=5>\"<font color=green>" + SearchWord + "</font>\"</font>","搜索结果");
		else show();
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
	 private void show(){
		 
	 }
}
