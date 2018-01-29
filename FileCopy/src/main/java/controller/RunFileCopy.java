package controller;

import java.io.File;
import java.nio.file.Path;

import model.CopyFilesBetweenDirectories;

public class RunFileCopy {

	public static void main(String[] args) {
		File fs = new File("c:/b_from/IMG_0374.JPG");
		File fd = new File("c:/b_to/"+fs.getName());
		File fsd = new File("c:/b_from/");
		File fdd = new File("c:/b_to/");

		
//		CopyFilesBetweenDirectories cfbd = new CopyFilesBetweenDirectories(fs, fd);
		CopyFilesBetweenDirectories cfbdd = new CopyFilesBetweenDirectories(fsd, fdd);
//		CopyFilesBetweenDirectories cfbd = new CopyFilesBetweenDirectories("c:\\b_from/IMG_0373.jpg", "c:\\b_to/");
//		cfbd.copyFile();
		cfbdd.copyFiles();
	}
}
