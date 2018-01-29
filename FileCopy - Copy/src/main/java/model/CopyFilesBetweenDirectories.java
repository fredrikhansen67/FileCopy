package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 
 * @author Fredrik
 * @version Java 1.7
 * Use SimpleFileVisitor to WalkFileTree
 */
public class CopyFilesBetweenDirectories extends SimpleFileVisitor<Path>{
	private static File scrFile;
	private static File destFile;

	public CopyFilesBetweenDirectories(String src, String dest) {
		CopyFilesBetweenDirectories.setScrFile(new File(src));
		CopyFilesBetweenDirectories.setDestFile(new File(dest));
	}

	public CopyFilesBetweenDirectories(File src, File dest) {
		setScrFile(src);
		setDestFile(dest);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
		try {
			Path destinationFile = destFile.toPath().resolve(scrFile.toPath().relativize(file));
			Files.copy(file, destinationFile);
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attributes) {
		try {
			Path destinationDirectory = destFile.toPath().resolve(scrFile.toPath().relativize(directory));
			Files.createDirectory(destinationDirectory);
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
		return FileVisitResult.CONTINUE;
	}
	/**
	 * Copies entire folder structure
	 */
	public void copyFiles() {
		try {
			Files.walkFileTree(scrFile.toPath(), new CopyFilesBetweenDirectories(scrFile, destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	/**
	 * Copies single file
	 */
	public void copyFile() {
		try {
			if(scrFile.exists()) {
				Files.copy(scrFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
			else
				System.out.println("missing sourcefile" + "source : "+scrFile.getAbsolutePath()+" destFile : "+destFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getScrFile() {
		return scrFile;
	}

	protected static void setScrFile(File scrFile) {
		CopyFilesBetweenDirectories.scrFile = scrFile;
	}

	public static File getDestFile() {
		return destFile;
	}

	protected static void setDestFile(File destFile) {
		CopyFilesBetweenDirectories.destFile = destFile;
	}
}


