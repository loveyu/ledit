package org.loveyu;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class SwingConsole {

	public static void run(final JFrame f, final int width, final int height) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				f.setTitle(null);

				// ��ȡ��Ļλ��
				Dimension screenSize = Toolkit.getDefaultToolkit()
						.getScreenSize();
				f.addWindowListener(Info.WindowListener);
				f.setLocation((int) ((screenSize.getWidth() - width) / 2.62),
						(int) ((screenSize.getHeight() - height) / 2.62));
				f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				f.setSize(width, height);
				f.setVisible(true);
			}
		});
	}
}
