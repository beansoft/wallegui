package util;


import java.io.*;

public class StringUtil {

	/**
	 * Check if the string is null or empty.
	 */
	public static boolean isEmpty(String input) {
		return (input == null || input.length() == 0);
	}

	/**
	 * 判断多个字符串或字符串数组是否为空.
	 *
	 * @param str
	 * @return
	 */
	public static boolean isAnyEmpty(String... str) {
		boolean isEmpty = false;
		for (int i = 0; i < str.length; i++) {
			if (isEmpty(str[i])) {
				isEmpty = true;
				break;
			}
		}
		return isEmpty;
	}

	/**
	 * Get the file name in the given file path name.
	 * Example input:
	 * D:\\A.txt
	 * D:A.txt
	 * D:/A.txt
	 * A.txt
	 * All the  above's output is:
	 * A.txt
	 */
	public static String getFileName(String filePath) {
		if(filePath == null) return null;
		// Convert other OS's file separator to current OS's
		if(filePath.indexOf('/') != -1) {
			filePath = filePath.replace('/', File.separatorChar);
		}
		if(filePath.indexOf('\\') != -1) {
			filePath = filePath.replace('\\', File.separatorChar);
		}
		// Convert the driver separator to separator
		if(filePath.indexOf(':') != -1) {
			filePath = filePath.replace(':', File.separatorChar);
		}
		// Get the file name
		if(filePath.indexOf(File.separator) != -1) {
			filePath = filePath.substring(filePath.lastIndexOf(File.separator) + 1
					, filePath.length());
		}
		return filePath;
	}

	/**
	 * Get the directory name in the given file path name.
	 * Example input:
	 * D:\\A.txt
	 * D:A.txt
	 * D:/A.txt
	 * A.txt
	 * All the  above's output is:
	 * A.txt
	 */
	public static String getDirName(String filePath) {
		if(filePath == null) return null;
		// Get the directory name
		if(filePath.indexOf(File.separator) != -1) {
			filePath = filePath.substring(0, filePath.lastIndexOf(File.separator) + 1);
		} else if(filePath.indexOf('/') != -1) {
			filePath = filePath.substring(0, filePath.lastIndexOf('/') + 1);
		} else if(filePath.indexOf('\\') != -1) {
			filePath = filePath.substring(0, filePath.lastIndexOf('\\') + 1);
		} else if(filePath.indexOf(':') != -1) {
			filePath = filePath.substring(0, filePath.lastIndexOf(':') + 1);
		} else {
			return null;
		}

		return filePath;
	}

	public static String getEntryName(String baseDir, String filePath) {
		if(StringUtil.isEmpty(baseDir)) return getFileName(filePath);
		try {
			File base = new File(baseDir);
			File temp = new File(filePath);
			String baseName = base.getCanonicalPath().replace(File.separatorChar, '/');
			String fileName = temp.getCanonicalPath().replace(File.separatorChar, '/');
			// Because sometimes the OS doesn't retured a directory name
			// with file separator, so we fix it
			if(temp.isDirectory()) {
				if(!fileName.endsWith("/")) {
					fileName += "/";
				}
			}
			// If baseName is c:/folder or c:/folder/, then entryDirName is folder or folder/
			String entryDirName = baseName.substring(baseName.lastIndexOf('/', baseName.length() - 2) + 1);
			// If fileName is c:/folder/a.txt(c:/folder/sub/a.txt),
			// then entryFileName is a.txt(sub/a.txt)
			String entryFileName = fileName.substring(baseName.length());
			return entryDirName + entryFileName;
		} catch(Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}


	/**
	 * Return the extension portion of the file's name .
	 *
	 * @see #getExtension
	 */
	public static String getExtension(String fileName) {
		if(fileName != null) {
			int i = fileName.lastIndexOf('.');
			if(i > 0 && i < fileName.length() - 1) {
				return fileName.substring(i+1).toLowerCase();
			}
		}
		return null;
	}

	public static boolean isDirectory(String filePath) {
		if(filePath == null) return false;
		// Convert other OS's file separator to current OS's
		if(filePath.indexOf('/') != -1) {
			filePath = filePath.replace('/', File.separatorChar);
		}
		if(filePath.indexOf('\\') != -1) {
			filePath = filePath.replace('\\', File.separatorChar);
		}
		// Convert the driver separator to separator
		if(filePath.indexOf(':') != -1) {
			filePath = filePath.replace(':', File.separatorChar);
		}
		return filePath.endsWith(File.separator);
	}

	public static void main(String[] args) {
		System.out.println(getEntryName("c:folder", "c:folder\\sub\\a.txt"));
		System.out.println(getEntryName("c:\\folder", "c:\\folder\\sub"));
	}

}