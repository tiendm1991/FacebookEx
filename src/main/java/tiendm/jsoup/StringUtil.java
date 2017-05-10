package tiendm.jsoup;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.Collator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static final String CHARFORM_NOHORN = "aaaaaaaaaaaaaaaaaeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyydAAAAAAAAAAAAAAAAAEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYYD";
	public static final String CHARFORM_UNICODE = "Ã Ã¡áº£Ã£áº¡Ã¢áº§áº¥áº©áº«áº­Äƒáº±áº¯áº³áºµáº·Ã¨Ã©áº»áº½áº¹Ãªï¿½?áº¿á»ƒá»…á»‡Ã¬Ã­á»‰Ä©á»‹Ã²Ã³ï¿½?Ãµï¿½?Ã´á»“á»‘á»•á»—á»™Æ¡ï¿½?á»›á»Ÿá»¡á»£Ã¹Ãºá»§Å©á»¥Æ°á»«á»©á»­á»¯á»±á»³Ã½á»·á»¹á»µÄ‘Ã€ï¿½?áº¢Ãƒáº Ã‚áº¦áº¤áº¨áºªáº¬Ä‚áº°áº®áº²áº´áº¶ÃˆÃ‰áººáº¼áº¸ÃŠá»€áº¾á»‚á»„á»†ÃŒï¿½?á»ˆÄ¨á»ŠÃ’Ã“á»ŽÃ•á»ŒÃ”á»’ï¿½?á»”á»–á»˜Æ á»œá»šá»žá» á»¢Ã™Ãšá»¦Å¨á»¤Æ¯á»ªá»¨á»¬á»®á»°á»²ï¿½?á»¶á»¸á»´ï¿½?";
	public static final String CHARFORM_TCVN = "ÂµÂ¸Â¶Â·Â¹Â©Ã‡ÃŠÃˆÃ‰Ã‹Â¨Â»Â¾Â¼Â½Ã†ÃŒï¿½?ÃŽï¿½?Ã‘ÂªÃ’Ã•Ã“Ã”Ã–Ã—ï¿½?Ã˜ÃœÃžÃŸÃ£Ã¡Ã¢Ã¤Â«Ã¥Ã¨Ã¦Ã§Ã©Â¬ÃªÃ­Ã«Ã¬Ã®Ã¯Ã³Ã±Ã²Ã´Â­ÃµÃ¸Ã¶Ã·Ã¹ÃºÃ½Ã»Ã¼Ã¾Â®ÂµÂ¸Â¶Â·Â¹Â©Ã‡ÃŠÃˆÃ‰Ã‹Â¨Â»Â¾Â¼Â½Ã†ÃŒï¿½?ÃŽï¿½?Ã‘ÂªÃ’Ã•Ã“Ã”Ã–Ã—ï¿½?Ã˜ÃœÃžÃŸÃ£Ã¡Ã¢Ã¤Â«Ã¥Ã¨Ã¦Ã§Ã©Â¬ÃªÃ­Ã«Ã¬Ã®Ã¯Ã³Ã±Ã²Ã´Â­ÃµÃ¸Ã¶Ã·Ã¹ÃºÃ½Ã»Ã¼Ã¾Â®";
	public static final int ALIGN_CENTER = 0;
	public static final int ALIGN_LEFT = 1;
	public static final int ALIGN_RIGHT = 2;


	public static String format(long lngNumber, String strPattern) {
		DecimalFormat fmt = new DecimalFormat(strPattern);
		return fmt.format(lngNumber);
	}

	public static String format(double dblNumber, String strPattern) {
		DecimalFormat fmt = new DecimalFormat(strPattern);
		return fmt.format(dblNumber);
	}

	public static String replaceAll(String strSrc, String strFind, String strReplace) {
		if ((strFind == null) || (strFind.length() == 0))
			return strSrc;
		int iLocation = 0;
		int iPrevLocation = 0;
		StringBuffer strResult = new StringBuffer();
		while ((iLocation = strSrc.indexOf(strFind, iLocation)) >= 0) {
			strResult.append(strSrc.substring(iPrevLocation, iLocation));
			strResult.append(strReplace);
			iLocation += strFind.length();
			iPrevLocation = iLocation;
		}
		strResult.append(strSrc.substring(iPrevLocation, strSrc.length()));
		return strResult.toString();
	}

	public static String replaceAll(String strSrc, String strFind, String strReplace, int iMaxReplacement) {
		int iLocation = 0;
		if ((strFind == null) || (strFind.length() == 0))
			return strSrc;
		int iPrevLocation = 0;
		int iCount = 0;
		StringBuffer strResult = new StringBuffer();
		while (((iLocation = strSrc.indexOf(strFind, iLocation)) >= 0) && (iCount < iMaxReplacement)) {
			strResult.append(strSrc.substring(iPrevLocation, iLocation));
			strResult.append(strReplace);
			++iCount;
			iLocation += strFind.length();
			iPrevLocation = iLocation;
		}
		strResult.append(strSrc.substring(iPrevLocation, strSrc.length()));
		return strResult.toString();
	}

	public static String replaceAllIgnoreCase(String strSrc, String strFind, String strReplace) {
		if ((strFind == null) || (strFind.length() == 0))
			return strSrc;
		String strSrcUpper = strSrc.toUpperCase();
		strFind = strFind.toUpperCase();

		int iLocation = 0;
		int iPrevLocation = 0;
		StringBuffer strResult = new StringBuffer();
		while ((iLocation = strSrcUpper.indexOf(strFind, iLocation)) >= 0) {
			strResult.append(strSrc.substring(iPrevLocation, iLocation));
			strResult.append(strReplace);
			iLocation += strFind.length();
			iPrevLocation = iLocation;
		}
		strResult.append(strSrc.substring(iPrevLocation, strSrc.length()));
		return strResult.toString();
	}

	public static String nvl(Object objInput, String strNullValue) {
		if (objInput == null)
			return strNullValue;
		return objInput.toString();
	}

	public static int indexOfLetter(String strSource, int iOffset) {
		while (iOffset < strSource.length()) {
			char c = strSource.charAt(iOffset);
			if (c > ' ') {
				return iOffset;
			}
			++iOffset;
		}
		return -1;
	}

	public static String[] toStringArray(String strSource, String strSeparator) {
		Vector vtReturn = toStringVector(strSource, strSeparator);
		String[] strReturn = new String[vtReturn.size()];
		for (int iIndex = 0; iIndex < strReturn.length; ++iIndex)
			strReturn[iIndex] = ((String) vtReturn.elementAt(iIndex));
		return strReturn;
	}

	public static Vector toStringVector(String strSource, String strSeparator) {
		Vector vtReturn = new Vector();
		int iIndex = 0;
		int iLastIndex = 0;
		while ((iIndex = strSource.indexOf(strSeparator, iLastIndex)) >= 0) {
			vtReturn.addElement(strSource.substring(iLastIndex, iIndex).trim());
			iLastIndex = iIndex + strSeparator.length();
		}
		if (iLastIndex <= strSource.length())
			vtReturn.addElement(strSource.substring(iLastIndex, strSource.length()).trim());
		return vtReturn;
	}

	public static String[] toStringArray(String strSource) {
		return toStringArray(strSource, ",");
	}

	public static Vector toStringVector(String strSource) {
		return toStringVector(strSource, ",");
	}

	public static String convertCharForm(String strSource, String strCharformSource, String strCharformDestination) {
		if (strSource == null)
			return null;
		int iLength = strSource.length();
		int iResult = 0;
		StringBuffer strReturn = new StringBuffer();
		for (int iIndex = 0; iIndex < iLength; ++iIndex) {
			char c = strSource.charAt(iIndex);
			if ((iResult = strCharformSource.indexOf(c)) >= 0)
				strReturn.append(strCharformDestination.charAt(iResult));
			else
				strReturn.append(c);
		}
		return strReturn.toString();
	}

	public static String unicodeToTCVN(String strSource) {
		return convertCharForm(strSource,
				"Ã Ã¡áº£Ã£áº¡Ã¢áº§áº¥áº©áº«áº­Äƒáº±áº¯áº³áºµáº·Ã¨Ã©áº»áº½áº¹Ãªï¿½?áº¿á»ƒá»…á»‡Ã¬Ã­á»‰Ä©á»‹Ã²Ã³ï¿½?Ãµï¿½?Ã´á»“á»‘á»•á»—á»™Æ¡ï¿½?á»›á»Ÿá»¡á»£Ã¹Ãºá»§Å©á»¥Æ°á»«á»©á»­á»¯á»±á»³Ã½á»·á»¹á»µÄ‘Ã€ï¿½?áº¢Ãƒáº Ã‚áº¦áº¤áº¨áºªáº¬Ä‚áº°áº®áº²áº´áº¶ÃˆÃ‰áººáº¼áº¸ÃŠá»€áº¾á»‚á»„á»†ÃŒï¿½?á»ˆÄ¨á»ŠÃ’Ã“á»ŽÃ•á»ŒÃ”á»’ï¿½?á»”á»–á»˜Æ á»œá»šá»žá» á»¢Ã™Ãšá»¦Å¨á»¤Æ¯á»ªá»¨á»¬á»®á»°á»²ï¿½?á»¶á»¸á»´ï¿½?",
				"ÂµÂ¸Â¶Â·Â¹Â©Ã‡ÃŠÃˆÃ‰Ã‹Â¨Â»Â¾Â¼Â½Ã†ÃŒï¿½?ÃŽï¿½?Ã‘ÂªÃ’Ã•Ã“Ã”Ã–Ã—ï¿½?Ã˜ÃœÃžÃŸÃ£Ã¡Ã¢Ã¤Â«Ã¥Ã¨Ã¦Ã§Ã©Â¬ÃªÃ­Ã«Ã¬Ã®Ã¯Ã³Ã±Ã²Ã´Â­ÃµÃ¸Ã¶Ã·Ã¹ÃºÃ½Ã»Ã¼Ã¾Â®ÂµÂ¸Â¶Â·Â¹Â©Ã‡ÃŠÃˆÃ‰Ã‹Â¨Â»Â¾Â¼Â½Ã†ÃŒï¿½?ÃŽï¿½?Ã‘ÂªÃ’Ã•Ã“Ã”Ã–Ã—ï¿½?Ã˜ÃœÃžÃŸÃ£Ã¡Ã¢Ã¤Â«Ã¥Ã¨Ã¦Ã§Ã©Â¬ÃªÃ­Ã«Ã¬Ã®Ã¯Ã³Ã±Ã²Ã´Â­ÃµÃ¸Ã¶Ã·Ã¹ÃºÃ½Ã»Ã¼Ã¾Â®");
	}

	public static String tcvnToUnicode(String strSource) {
		return convertCharForm(strSource,
				"ÂµÂ¸Â¶Â·Â¹Â©Ã‡ÃŠÃˆÃ‰Ã‹Â¨Â»Â¾Â¼Â½Ã†ÃŒï¿½?ÃŽï¿½?Ã‘ÂªÃ’Ã•Ã“Ã”Ã–Ã—ï¿½?Ã˜ÃœÃžÃŸÃ£Ã¡Ã¢Ã¤Â«Ã¥Ã¨Ã¦Ã§Ã©Â¬ÃªÃ­Ã«Ã¬Ã®Ã¯Ã³Ã±Ã²Ã´Â­ÃµÃ¸Ã¶Ã·Ã¹ÃºÃ½Ã»Ã¼Ã¾Â®ÂµÂ¸Â¶Â·Â¹Â©Ã‡ÃŠÃˆÃ‰Ã‹Â¨Â»Â¾Â¼Â½Ã†ÃŒï¿½?ÃŽï¿½?Ã‘ÂªÃ’Ã•Ã“Ã”Ã–Ã—ï¿½?Ã˜ÃœÃžÃŸÃ£Ã¡Ã¢Ã¤Â«Ã¥Ã¨Ã¦Ã§Ã©Â¬ÃªÃ­Ã«Ã¬Ã®Ã¯Ã³Ã±Ã²Ã´Â­ÃµÃ¸Ã¶Ã·Ã¹ÃºÃ½Ã»Ã¼Ã¾Â®",
				"Ã Ã¡áº£Ã£áº¡Ã¢áº§áº¥áº©áº«áº­Äƒáº±áº¯áº³áºµáº·Ã¨Ã©áº»áº½áº¹Ãªï¿½?áº¿á»ƒá»…á»‡Ã¬Ã­á»‰Ä©á»‹Ã²Ã³ï¿½?Ãµï¿½?Ã´á»“á»‘á»•á»—á»™Æ¡ï¿½?á»›á»Ÿá»¡á»£Ã¹Ãºá»§Å©á»¥Æ°á»«á»©á»­á»¯á»±á»³Ã½á»·á»¹á»µÄ‘Ã€ï¿½?áº¢Ãƒáº Ã‚áº¦áº¤áº¨áºªáº¬Ä‚áº°áº®áº²áº´áº¶ÃˆÃ‰áººáº¼áº¸ÃŠá»€áº¾á»‚á»„á»†ÃŒï¿½?á»ˆÄ¨á»ŠÃ’Ã“á»ŽÃ•á»ŒÃ”á»’ï¿½?á»”á»–á»˜Æ á»œá»šá»žá» á»¢Ã™Ãšá»¦Å¨á»¤Æ¯á»ªá»¨á»¬á»®á»°á»²ï¿½?á»¶á»¸á»´ï¿½?");
	}

	public static String clearHornUnicode(String strSource) {
		return convertCharForm(strSource,
				"Ã Ã¡áº£Ã£áº¡Ã¢áº§áº¥áº©áº«áº­Äƒáº±áº¯áº³áºµáº·Ã¨Ã©áº»áº½áº¹Ãªï¿½?áº¿á»ƒá»…á»‡Ã¬Ã­á»‰Ä©á»‹Ã²Ã³ï¿½?Ãµï¿½?Ã´á»“á»‘á»•á»—á»™Æ¡ï¿½?á»›á»Ÿá»¡á»£Ã¹Ãºá»§Å©á»¥Æ°á»«á»©á»­á»¯á»±á»³Ã½á»·á»¹á»µÄ‘Ã€ï¿½?áº¢Ãƒáº Ã‚áº¦áº¤áº¨áºªáº¬Ä‚áº°áº®áº²áº´áº¶ÃˆÃ‰áººáº¼áº¸ÃŠá»€áº¾á»‚á»„á»†ÃŒï¿½?á»ˆÄ¨á»ŠÃ’Ã“á»ŽÃ•á»ŒÃ”á»’ï¿½?á»”á»–á»˜Æ á»œá»šá»žá» á»¢Ã™Ãšá»¦Å¨á»¤Æ¯á»ªá»¨á»¬á»®á»°á»²ï¿½?á»¶á»¸á»´ï¿½?",
				"aaaaaaaaaaaaaaaaaeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyydAAAAAAAAAAAAAAAAAEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYYD");
	}

	public static String clearHornTCVN(String strSource) {
		return convertCharForm(strSource,
				"ÂµÂ¸Â¶Â·Â¹Â©Ã‡ÃŠÃˆÃ‰Ã‹Â¨Â»Â¾Â¼Â½Ã†ÃŒï¿½?ÃŽï¿½?Ã‘ÂªÃ’Ã•Ã“Ã”Ã–Ã—ï¿½?Ã˜ÃœÃžÃŸÃ£Ã¡Ã¢Ã¤Â«Ã¥Ã¨Ã¦Ã§Ã©Â¬ÃªÃ­Ã«Ã¬Ã®Ã¯Ã³Ã±Ã²Ã´Â­ÃµÃ¸Ã¶Ã·Ã¹ÃºÃ½Ã»Ã¼Ã¾Â®ÂµÂ¸Â¶Â·Â¹Â©Ã‡ÃŠÃˆÃ‰Ã‹Â¨Â»Â¾Â¼Â½Ã†ÃŒï¿½?ÃŽï¿½?Ã‘ÂªÃ’Ã•Ã“Ã”Ã–Ã—ï¿½?Ã˜ÃœÃžÃŸÃ£Ã¡Ã¢Ã¤Â«Ã¥Ã¨Ã¦Ã§Ã©Â¬ÃªÃ­Ã«Ã¬Ã®Ã¯Ã³Ã±Ã²Ã´Â­ÃµÃ¸Ã¶Ã·Ã¹ÃºÃ½Ã»Ã¼Ã¾Â®",
				"aaaaaaaaaaaaaaaaaeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyydAAAAAAAAAAAAAAAAAEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYYD");
	}

	public static String pronounceVietnameseNumber(long lNumber) {
		String[] strUnit = { "", "nghÃ¬n", "triá»‡u", "tá»·", "nghÃ¬n tá»·", "triá»‡u tá»·", "nghÃ¬n triá»‡u tá»·",
				"tá»· tá»·" };

		byte[] btDecimalNumber = new byte[30];
		byte btDecimalCount = 0;
		boolean bNegative = lNumber < 0L;
		if (bNegative)
			lNumber = -lNumber;
		while (lNumber > 0L) {
			byte btValue = (byte) (int) (lNumber - (10L * lNumber / 10L));
			lNumber /= 10L;
			btDecimalCount = (byte) (btDecimalCount + 1);
			btDecimalNumber[btDecimalCount] = btValue;
		}

		String strReturn = "";
		int iUnitIndex = 0;
		while ((iUnitIndex < strUnit.length) && (iUnitIndex * 3 < btDecimalCount)) {
			String str = pronounceVietnameseNumber(btDecimalNumber[(iUnitIndex * 3)],
					btDecimalNumber[(iUnitIndex * 3 + 1)], btDecimalNumber[(iUnitIndex * 3 + 2)],
					iUnitIndex * 3 + 2 < btDecimalCount);

			if (str.length() > 0) {
				if (strReturn.length() > 0)
					strReturn = str + " " + strUnit[iUnitIndex] + " " + strReturn;
				else
					strReturn = str + " " + strUnit[iUnitIndex];
			}
			++iUnitIndex;
		}
		if (bNegative)
			strReturn = "Ã¢m " + strReturn;
		return strReturn;
	}

	private static String pronounceVietnameseNumber(byte bUnit, byte bTen, byte bHundred, boolean bMax) {
		if ((bUnit == 0) && (bTen == 0) && (bHundred == 0)) {
			return "";
		}
		String[] strUnitSuffix = { "", "má»™t", "hai", "ba", "tÆ°", "lÄƒm", "sÃ¡u", "báº£y", "tÃ¡m", "chÃ­n" };

		String[] strUnitTen = { "", "mÆ°ï¿½?i má»™t", "mÆ°ï¿½?i hai", "mÆ°ï¿½?i ba", "mÆ°ï¿½?i bá»‘n", "mÆ°ï¿½?i lÄƒm",
				"mÆ°ï¿½?i sÃ¡u", "mÆ°ï¿½?i báº£y", "mÆ°ï¿½?i tÃ¡m", "mÆ°ï¿½?i chÃ­n" };

		String[] strUnit = { "", "má»™t", "hai", "ba", "bá»‘n", "nÄƒm", "sÃ¡u", "báº£y", "tÃ¡m", "chÃ­n" };
		String[] strTenFirst = { "", "mÆ°ï¿½?i má»™t", "hai mÆ°Æ¡i má»‘t", "ba mÆ°Æ¡i má»‘t", "bá»‘n mÆ°Æ¡i má»‘t",
				"nÄƒm mÆ°Æ¡i má»‘t", "sÃ¡u mÆ°Æ¡i má»‘t", "báº£y mÆ°Æ¡i má»‘t", "tÃ¡m mÆ°Æ¡i má»‘t",
				"chÃ­n mÆ°Æ¡i má»‘t" };

		String[] strTen = { "", "mÆ°ï¿½?i", "hai mÆ°Æ¡i", "ba mÆ°Æ¡i", "bá»‘n mÆ°Æ¡i", "nÄƒm mÆ°Æ¡i", "sÃ¡u mÆ°Æ¡i",
				"báº£y mÆ°Æ¡i", "tÃ¡m mÆ°Æ¡i", "chÃ­n mÆ°Æ¡i" };

		String[] strHundred = { "khÃ´ng trÄƒm", "má»™t trÄƒm", "hai trÄƒm", "ba trÄƒm", "bá»‘n trÄƒm", "nÄƒm trÄƒm",
				"sÃ¡u trÄƒm", "báº£y trÄƒm", "tÃ¡m trÄƒm", "chÃ­n trÄƒm" };

		String strReturn = "";

		if ((bMax) || (bHundred > 0))
			strReturn = strHundred[bHundred];
		if (bTen > 0) {
			if (strReturn.length() > 0)
				strReturn = strReturn + " ";
			if (bUnit > 0) {
				if (bTen == 1) {
					strReturn = strReturn + strUnitTen[bUnit];
				} else if (bUnit == 1)
					strReturn = strReturn + strTenFirst[bTen];
				else
					strReturn = strReturn + strTen[bTen] + " " + strUnitSuffix[bUnit];
			} else
				strReturn = strReturn + strTen[bTen];
		} else if (bUnit > 0) {
			if (strReturn.length() > 0)
				strReturn = strReturn + " linh " + strUnit[bUnit];
			else {
				strReturn = strUnit[bUnit];
			}
		}
		return strReturn;
	}

	public static String align(String str, int iAlignment, int iLength) {
		if (str == null)
			return null;
		if (str.length() > iLength)
			return str.substring(0, iLength);
		StringBuffer buf = new StringBuffer();
		if (iAlignment == 0) {
			int iFirstLength = (iLength - str.length()) / 2;
			for (int iIndex = 0; iIndex < iFirstLength; ++iIndex)
				buf.append(" ");
			buf.append(str);
			for (int iIndex = str.length() + iFirstLength; iIndex < iLength; ++iIndex)
				buf.append(" ");
		} else if (iAlignment == 2) {
			iLength -= str.length();
			for (int iIndex = 0; iIndex < iLength; ++iIndex)
				buf.append(" ");
			buf.append(str);
		} else {
			buf.append(str);
			for (int iIndex = str.length(); iIndex < iLength; ++iIndex)
				buf.append(" ");
		}
		return buf.toString();
	}

	public static boolean stringIsNullOrEmty(String str) {
		if (str == null) {
			return true;
		}

		return (str.length() <= 0);
	}

	public static boolean stringIsNullOrEmty(Object str) {
		if (str == null) {
			return true;
		}

		return (str.toString().length() <= 0);
	}


	public static ArrayList<String> stringToArrayList(String strData, String chSplit) {
		ArrayList list = new ArrayList();
		try {
			String[] arr = strData.split(chSplit);
			for (String string : arr)
				list.add(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Long stringHMSToMillis(String strHMS) {
		if (stringIsNullOrEmty(strHMS)) {
			return null;
		}
		String[] arrStr = strHMS.split(":");
		if (arrStr.length == 3) {
			int h = Integer.parseInt(arrStr[0]);
			int m = Integer.parseInt(arrStr[1]);
			int s = Integer.parseInt(arrStr[2]);
			long mH = h * 3600000;
			long mM = m * 60000;
			long mS = s * 1000;
			return Long.valueOf(mH + mM + mS);
		}
		return null;
	}

	public static List<String> strCodeToList(String str) {
		List list = new ArrayList();
		String[] arrStr = str.split(",");
		for (String string : arrStr) {
			list.add(string);
		}
		return list;
	}

	public static String convertListToString(List<Object> list) {
		String data = "";
		if ((list != null) && (!(list.isEmpty()))) {
			for (int i = 0; i < list.size(); ++i) {
				if (i == list.size() - 1) {
					data = data + "'" + list.get(i) + "'";
				} else {
					data = data + "'" + list.get(i) + "',";
				}
			}

		}

		return data;
	}

	public static String listToString(List<String> list, String strSeparator) {
		String str = null;
		for (Object string : list) {
			if (str == null) {
				if (string instanceof BigDecimal)
					str = String.valueOf(string);
				else {
					str = string.toString();
				}
			} else if (string instanceof BigDecimal)
				str = str + strSeparator + String.valueOf(string);
			else {
				str = str + strSeparator + string.toString();
			}
		}

		return str;
	}

	public static String listToString(List<String> list, String strSeparator, String strChar) {
		String str = null;
		for (String string : list) {
			if (str == null)
				str = strChar + string + strChar;
			else {
				str = str + strSeparator + strChar + string + strChar;
			}
		}
		return str;
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();

		int randomNum = rand.nextInt(max - min + 1) + min;

		return randomNum;
	}
	
	public static String findId(String url){
		Pattern p = Pattern.compile("id=+(\\d*)");
		Matcher m = p.matcher(url);
		if (m.find()) {
		    return m.group(1);
		}
		return "";
	}
}
