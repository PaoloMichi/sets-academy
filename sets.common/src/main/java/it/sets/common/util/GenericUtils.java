package it.sets.common.util;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GenericUtils {

	public static final String FULL_DATE_FORMATTER = "dd-MM-yyyy HH:mm:ss:SSS";
	public static final String FULL_REVERSE_DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String FULL_REVERSE_DATE_FORMATTER_FILENAME = "yyyyMMdd-HHmmss";
	public static final String MEDIUM_DATE_FORMATTER = "dd-MM-yyyy HH:mm:ss";
	public static final String SHORT_DATE_FORMATTER = "dd-MM-yyyy";
	public static final String SHORT_REVERSE_DATE_FORMATTER = "yyyy-MM-dd";
	public static final String SHORT_SLASH_DATE_FORMATTER = "dd/MM/yyyy";
	
	public static String checkStringNullOrEmpty(String elem) {
		if (null != elem && !"".equalsIgnoreCase(elem))
			return elem;
		return null;
	}
	
	public static Double checkDoubleNullOrEmpty(String elem) {
		if (null != elem && !"".equalsIgnoreCase(elem))
			Double.parseDouble(elem.replaceAll(",","."));
		return null;
	}
	
	public static Date checkDateNullOrEmpty(String elem, String formatter) {
		return GenericUtils.getDateFromString(elem, formatter);
	}
	
	public static String getCurrentTimeInString() {
		return getCurrentTimeInString(FULL_DATE_FORMATTER);
	}
	
	public static String getCurrentTimeInString(String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}

	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	public static long getTimeMillisFromStringDate(String strDate) {
		return getTimeMillisFromStringDate(strDate, MEDIUM_DATE_FORMATTER);
	}
	
	public static long getTimeMillisFromStringDate(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date.getTime();
	}

	public static Date getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	public static String getStringFromDate(Date date) {
		return getStringFromDate(date, FULL_DATE_FORMATTER);
	}

	public static String getStringFromDate(Date date, String format) {
		if (null == date)
			return null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String getStringFromLocalDate(LocalDateTime localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		return localDate.format(formatter);
	}

	public static Date getDateFromString(String strDate) {
		try {
			return new SimpleDateFormat(FULL_DATE_FORMATTER).parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static Date getDateFromString(String strDate, String formatter) {
		if (null != strDate && !"".equalsIgnoreCase(strDate))
			try {
				return new SimpleDateFormat(formatter).parse(strDate);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		return null;
		
	}

	public static Date getDateFromStringShort(String strDate) {
		try {
			return new SimpleDateFormat(SHORT_SLASH_DATE_FORMATTER).parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Date getDateFromStringCalendar(String strDate) {
		Date dt = getDateFromString(strDate, SHORT_REVERSE_DATE_FORMATTER);
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		dt = c.getTime();
		return dt;
	}
	
	/**
	 * Generate a new fileName: fileName.extension --> fileName_{DATE}.extension
	 * 
	 * @param fileName - fileName with extension --> fileName.ext
	 * @param extension - extension with dot (.) --> .ext
	 * @return
	 */
	public static String generateFileNameWithDate(String fileName, String extension) {
		String dateString = getCurrentTimeInString(GenericUtils.FULL_REVERSE_DATE_FORMATTER_FILENAME);
		String newSuffix = new StringBuilder("_").append(dateString).append(extension).toString();
		return fileName.replace(extension, newSuffix);
	}

	public static Class<?> getClassForName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(new StringBuilder("Unable to get Class for name ").append(className).toString(), e);
		}
	}

	public static String removeExtensionFromFileName(String fileName) {
		return fileName.split("\\.")[0];
	}
	
	public static Long[] splitIdByUnderscore(String id) {
		if (id.indexOf("_") > 0) {
			String[] idSplitted = id.split("_");
			Long[] ids = new Long[idSplitted.length];
			for (int i = 1; idSplitted.length >= i; i++) {
				ids[i-1] = Long.valueOf(idSplitted[i-1]);
			}
			return ids;
		} else {
			return new Long[]{Long.valueOf(id)};
		}
	}

	public static int getRandomInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
	
	public static String getRandomIntString(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return String.valueOf(randomNum);
	}
	
	public static String createGenericCorrelationId() {
		Hashids hashids = new Hashids(getRandomIntString(1, 1000), 10);
		return hashids.encode(getCurrentTimeMillis());
	}
	
	public static String createCorrelationId(String username) {
		Hashids hashids = new Hashids(username, 10);
		return hashids.encode(getCurrentTimeMillis());
	}
	
	public static <T extends Enum<T>> List<T> stringListToEnumList(Class<T> clazz, List<String> stringList) {
	    List<T> enumList = new ArrayList<>();
	    for (String elem : stringList) {
	    	enumList.add(Enum.valueOf(clazz, elem));
	    }
	    return enumList;
	}
	
	public static String generateKeyFromString(String string) {
		return generateKeyFromString(string, 20);
	}
	
	public static String generateKeyFromString(String string, int length) {
		Hashids hashids = new Hashids(string, length);
		return hashids.encode(10, 100, 1000);
	}
	
	public static boolean validateKeyFromString(String string, String key) {
		return validateKeyFromString(string, key, 20);
	}
	
	public static boolean validateKeyFromString(String string, String key, int length) {
		Hashids hashids = new Hashids(string, length);
		long[] decoded = hashids.decode(key);
		if (decoded.length < 1 || decoded[0] != 10 || decoded[1] != 100 || decoded[2] != 1000)
			return false;
		else 
			return true;
	}
	
	public static String generateRandomPassword(Integer length) {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        return sb.toString();
	}

}
