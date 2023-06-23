package it.sets.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.sets.common.exception.FileException;

public class FileUtils {

	public static Path load(Path location, String fileName) {
		if (!Files.exists(location)) {
			try {
				Files.createDirectories(location);
			} catch (IOException e) {
				throw new FileException(new StringBuilder("Failed to create folder file ").append(fileName).toString(), e);
			}
		}
		return location.resolve(fileName);
	}
	
	public static String store(Path location, MultipartFile file) {
		return store(location, file, null);
	}
	
	public static String store(Path location, MultipartFile file, String fileName) {
		if (null == fileName)
			fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty())
				throw new FileException(new StringBuilder("Failed to store empty file ").append(fileName).toString());
			if (fileName.contains(".."))
				throw new FileException(new StringBuilder("Cannot store file with relative path outside current directory ").append(fileName).toString());
            Files.write(FileUtils.load(location, fileName), file.getBytes());
		} catch (IOException e) {
			throw new FileException(new StringBuilder("Failed to store file ").append(fileName).toString(), e);
		}
		return new StringBuilder(location.toString()).append("/").append(fileName).toString();
	}
	
	public static void moveFile(String stringActualDirPath, String actualFileName, String stringNextDirPath, String stringNextFileName) {
		moveFile(stringActualDirPath, actualFileName, stringNextDirPath, stringNextFileName, false);
	}
	
	public static void moveFile(String stringActualDirPath, String actualFileName, String stringNextDirPath, String stringNextFileName, boolean removeDirIfEmpty) {
		moveFile(Paths.get(stringActualDirPath), actualFileName, Paths.get(stringNextDirPath), stringNextFileName, removeDirIfEmpty);
	}
	
	public static void moveFile(Path actualDirPath, String actualFileName, Path nextDirPath, String stringNextFileName, boolean removeDirIfEmpty) {
		try {
			Files.move(load(actualDirPath, actualFileName), load(nextDirPath, stringNextFileName));
		} catch (IOException e) {
			throw new FileException(new StringBuilder("Could not move file: ").append(actualFileName).toString(), e);
		}
		if (removeDirIfEmpty) {
			try (DirectoryStream<Path> entries = Files.newDirectoryStream(actualDirPath)) {
				if (!entries.iterator().hasNext())
					Files.delete(actualDirPath);
			} catch (IOException e) {
				throw new FileException(new StringBuilder("Unable to remove folder: ").append(actualFileName).toString(), e);
			}
		}
	}
	
	public static void removeDirAndContent(String stringDirPath) {
		removeDirAndContent(Paths.get(stringDirPath));
	}
	
	public static void removeDirAndContent(Path dirPath) {
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(dirPath)) {
			for(Path entry : entries) {
				Files.delete(entry);
			}
			Files.delete(dirPath);
		} catch (IOException e) {
			throw new FileException(new StringBuilder("Unable to remove element: ").append(e.getLocalizedMessage()).toString(), e);
		}
	}
	
	public static void removeFile(String stringDirPath, String fileName, boolean removeDirIfEmpty) {
		Path dirPath = Paths.get(stringDirPath);
		try {
			Files.delete(load(dirPath, fileName));
		} catch (IOException e) {
			throw new FileException("Could not delete file", e);
		}
		if (removeDirIfEmpty) {
			try (DirectoryStream<Path> entries = Files.newDirectoryStream(dirPath)) {
				if (!entries.iterator().hasNext())
					Files.delete(dirPath);
			} catch (IOException e) {
				throw new FileException(new StringBuilder("Unable to remove folder: ").append(stringDirPath).toString(), e);
			}
		}
	}
	
	public static File convertMultipartToFile(String tmpPath, MultipartFile file) {
		File convFile = new File(new StringBuilder(tmpPath).append(File.separator).append(file.getOriginalFilename()).toString());
		if (!new File(tmpPath).exists())
			new File(tmpPath).mkdir();
		FileOutputStream fos = null;
		try {
			convFile.createNewFile();
			fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(new StringBuilder("Cannot convert MultipartFile ").append(file.getOriginalFilename()).append(" to FileOutputStream").toString(), e);
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new RuntimeException("Cannot close FileOutputStream in conversionMultipartFile");
				}
			}
		}
		return convFile;
	}

}
