package org.loveyu;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileAction {
	InitShow f;
	String path;
	File file;
	JFileChooser chooser = new JFileChooser();
	int returnVal;

	public FileAction(InitShow parent) {
		// TODO Auto-generated constructor stub
		f = parent;
	}

	public String getFile() {
		CloseFile(true);
		chooser.setFileFilter(new FileNameExtensionFilter("Text type file",
				"txt", "js", "html", "html", "php", "css", "bat", "java", "xml"));
		if (Info.dir != null)
			chooser.setCurrentDirectory(new File(Info.dir));
		returnVal = chooser.showOpenDialog(f);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getPath();
			Message.out("You chose to open this file: " + path);
			Info.path = path;
			Info.dir = chooser.getSelectedFile().getParent();
			return path;
		} else {
			Message.out("file chose action had cancel");
		}
		return null;
	}

	public boolean saveFile() {
		if (Info.path == null) {
			do {
				if (Info.dir != null)
					chooser.setCurrentDirectory(new File(Info.dir));
				returnVal = chooser.showSaveDialog(f);
				if (JFileChooser.APPROVE_OPTION != returnVal)
					break;
				Info.dir = chooser.getSelectedFile().getParent();
				if (chooser.getSelectedFile().isFile()) {
					Message.out("文件已存在  :"
							+ chooser.getSelectedFile().getPath());

					if (Message.YES_NO("确定覆盖文件？", "覆盖文件？", "red") == Message.OK_OPTION) {
						Message.out("chose Yes");
						break;
					} else {
						Message.out("chose NO");
						continue;
					}
				} else
					break;
			} while (JFileChooser.CANCEL_OPTION != returnVal);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				Message.out("save file to:"
						+ chooser.getSelectedFile().getPath());
				Info.path = chooser.getSelectedFile().getPath();
				Info.file_title = chooser.getSelectedFile().getName();
				return saveFile();
			}
			Message.out("give up save file");
			return false;
		} else {
			Message.out("begin write file :" + Info.path);

			PrintWriter out;
			try {
				out = new PrintWriter(new File(Info.path).getAbsoluteFile());
				out.print(Info.f.text.t.getText());
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}

			f.setTitle("已保存" + " - " + Info.file_title);
			Info.init_content = Info.f.text.t.getText();
			Message.out("succuffuly save file");
		}
		return true;
	}

	public boolean ReadFile() {
		file = new File(path);
		Info.path = path;
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));

			try {
				String s;
				Info.init_content = "";
				while ((s = in.readLine()) != null) {
					Info.init_content += s + "\n";
				}
				Info.emptyDoc = Info.init_content.isEmpty();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		// Message.out(Info.init_content);
		f.text.t.setText(Info.init_content);
		f.setTitle(file.getName());
		Info.file_title = file.getName();
		return true;
	}

	public boolean CloseFile(boolean clearFile) {
		if (!Info.f.text.t.getText().equals(Info.init_content)) {
			Message.out("file is no save");
			if (Message.YES_NO("是否保存文件？", "保存文件？", null) == Message.OK_OPTION) {
				Message.out("chose Yes\nsave file");
				saveFile();
			} else {
				Message.out("chose NO\ngive up save file");
			}
		}
		if (clearFile)
			Info.ClearFileInfo();
		return true;
	}
}
