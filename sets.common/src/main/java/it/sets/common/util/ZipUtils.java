package it.sets.common.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

    private static final int BUFFER_SIZE = 4096;
//    private static final String MACOSX = "__MACOSX";

    /**
     * Unzip file from FileSystem file
     * @param sourceZipPath - 	source path directory+zipName es. "directory/zipFileName.zip"
     * @param destDirPath	-	destination directory name es. "directory/subDir"
     * @throws IOException
     */
    public static void unzipFromFileSystem(String sourceZipPath, String destDirPath) throws IOException {
    	createDirIfNotExist(destDirPath);
        ZipInputStream zis = new ZipInputStream(new FileInputStream(sourceZipPath));
        cycleZis(zis, destDirPath);
        zis.close();
    }
    
    /**
     * Unzip file from input byte[]
     * @param data			-	input byte[]
     * @param destDirPath	-	destination directory name es. "directory/subDir"
     * @throws IOException
     */
    public static void unzipFromByteArray(byte[] data, String destDirPath) throws IOException {
    	createDirIfNotExist(destDirPath);
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(data));
        cycleZis(zis, destDirPath);
        zis.close();
    }
    
    private static void cycleZis(ZipInputStream zis, String destDirPath) throws IOException {
    	ZipEntry zipEntry = zis.getNextEntry();
    	while (zipEntry != null) {
    		String filePath = new StringBuilder(destDirPath).append(File.separator).toString();
    		if (zipEntry.getName().contains("/")) {
    			String[] entrySplittedStrings = zipEntry.getName().split("/");
    			filePath = new StringBuilder(filePath).append(entrySplittedStrings[entrySplittedStrings.length-1]).toString();
    		} else {
    			filePath = new StringBuilder(filePath).append(zipEntry.getName()).toString();
    		}
//            String filePath = destDirPath append File.separator append zipEntry.getName();
//            if (!zipEntry.isDirectory() && !zipEntry.getName().startsWith(MACOSX)) {
                // if the entry is a file, extracts it
                extractFile(zis, filePath);
//            } else {
//                // if the entry is a directory, make the directory
//                File dir = new File(filePath);
//                dir.mkdir();
//            }
            zis.closeEntry();
            zipEntry = zis.getNextEntry();
        }
    }
    
    private static void extractFile(ZipInputStream zis, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zis.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
    
    private static void createDirIfNotExist(String destDirPath) {
    	File destDir = new File(destDirPath);
        if (!destDir.exists()) {
            destDir.mkdir();
        }
    }
    
}
