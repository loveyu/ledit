package org.loveyu;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Menus {
	private JMenu[] menus = { new JMenu("�ļ�(F)"), new JMenu("�༭(E)"),
			new JMenu("����(A)") };
	private JMenuItem[][] items = {
			{ new JMenuItem("��", KeyEvent.VK_O),
					new JMenuItem("�½�", KeyEvent.VK_N),
					new JMenuItem("����", KeyEvent.VK_S), new JMenuItem("���"),
					new JMenuItem("�ر�", KeyEvent.VK_C),
					new JMenuItem("�˳�", KeyEvent.VK_X), },
			{ new JMenuItem("���"), new JMenuItem("����", KeyEvent.VK_F),
					new JMenuItem("�滻", KeyEvent.VK_R),
					new JMenuItem("����", KeyEvent.VK_Z),
					new JMenuItem("����", KeyEvent.VK_Y), },
			{ new JMenuItem("����"), new JMenuItem("����") } };

	private ActionListener menus_Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = ((JMenuItem) e.getSource()).getName();
			FileAction fa = new FileAction(Info.f);
			switch (name) {

			/* �ļ�������ʼ */
			case "Open":
				Message.out("file open action");
				String filePath = fa.getFile();
				if (filePath != null) {
					Message.out("succussful chose file " + filePath);

					if (fa.ReadFile()) {
						Message.out("Open file ok");
					}

				}
				break;
			case "New":
				fa.CloseFile(true);
				Info.f.setTitle("���ļ�  ");

				Message.out("new file action");
				break;
			case "Save":
				Message.out("save file action");
				if (fa.saveFile())
					Message.out("save file succussful");
				else
					Message.out("save file error");
				break;

			case "SaveOther":
				Message.out("save other file action");
				String tmp = Info.path;
				Info.path = null;
				if (fa.saveFile())
					Message.out("save other file succussful");
				else {
					Message.out("save other file error\nSet old file path");
					Info.path = tmp;
				}
				break;

			case "Close":

				fa.CloseFile(true);
				Message.out("close now file action");
				break;
			case "Exit":
				Message.out("Menus Exit action");
				Info.CloseAction();
				break;
			/* �ļ����� ���� */

			/* �༭������ʼ */
			case "Clear":
				Message.out("clear Text conent");
				Info.f.text.t.setText("");
				break;
			case "Find":
				Message.out("Find text");
				new Find();
				break;
			case "Replace":
				Message.out("Replace text");
				new Replace();
				break;
			case "Undo":
				Message.out("Undo Text");
				Info.text.undo();
				break;
			case "Redo":
				Message.out("Redo Text");
				Info.text.redo();
				break;
			/* �༭�������� */

			/* ���ڲ�����ʼ */
			case "Author":
				Message.Notice(("<html><font size=5>"
						+ "<center><b>LEdit Java �ı��༭��</b</center></font>"
						+ "<font size=4><br><left>���ߣ���־��"
						+ "<br>���ͣ�http://www.loveyu.org</left></font>"), "��������");
				break;

			case "Program":
				Message.Notice(
						"<html><font size=5>"
								+ "<center><b>LEdit Java �ı��༭��</b</center></font>"
								+ "<font size=4><br><left>����汾��0.1"
								+ "<br>��ַ��https://github.com/loveyu/ledit</left></font>",
						"����");
				break;
			/* ���ڲ������� */

			}
		}
	};

	public Menus() {
		// TODO Auto-generated constructor stub

		// number 0
		menus[0].add(items[0][0]);
		items[0][0].setName("Open");
		items[0][0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				Event.CTRL_MASK));

		menus[0].add(items[0][1]);
		items[0][1].setName("New");
		items[0][1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				Event.CTRL_MASK));

		menus[0].add(items[0][2]);
		items[0][2].setName("Save");
		items[0][2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				Event.CTRL_MASK));

		menus[0].add(items[0][3]);
		items[0][3].setName("SaveOther");

		menus[0].add(items[0][4]);
		items[0][4].setName("Close");
		items[0][4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				Event.ALT_MASK));

		menus[0].add(items[0][5]);
		items[0][5].setName("Exit");
		items[0][5].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				Event.ALT_MASK));

		// number 1
		menus[1].add(items[1][0]);
		items[1][0].setName("Clear");

		menus[1].add(items[1][1]);
		items[1][1].setName("Find");
		items[1][1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				Event.CTRL_MASK));

		menus[1].add(items[1][2]);
		items[1][2].setName("Replace");
		items[1][2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				Event.CTRL_MASK));

		menus[1].add(items[1][3]);
		items[1][3].setName("Undo");
		items[1][3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				Event.CTRL_MASK));

		menus[1].add(items[1][4]);
		items[1][4].setName("Redo");
		items[1][4].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				Event.CTRL_MASK));

		// number 2
		menus[2].add(items[2][0]);
		items[2][0].setName("Author");

		menus[2].add(items[2][1]);
		items[2][1].setName("Program");

		// number all
		menus[0].setName("File");
		menus[0].setMnemonic('f');

		menus[1].setName("Edit");
		menus[1].setMnemonic('E');

		menus[2].setName("About");
		menus[2].setMnemonic('A');
	}

	public JMenuBar getMenus() {
		for (JMenuItem[] JI : items)
			for (JMenuItem JI2 : JI)
				JI2.addActionListener(menus_Listener);
		JMenuBar mb = new JMenuBar();

		for (JMenu ME : menus)
			mb.add(ME);

		return (mb);
	}
}
