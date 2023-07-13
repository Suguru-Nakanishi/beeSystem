package tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日付操作クラス
 */
public class DateUtils {

	/** 日付ﾌｫｰﾏｯﾄ yyyy/MM/dd */
	public static final String DATE_FORMAT_YYYYMMDD_SLASH = "yyyy/MM/dd";
	/** 日付ﾌｫｰﾏｯﾄ yyyyMMdd */
	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	/** 日付ﾌｫｰﾏｯﾄ yy/MM/dd */
	public static final String DATE_FORMAT_YYMMDD_SLASH = "yy/MM/dd";
	/** 日付ﾌｫｰﾏｯﾄ yyMMdd */
	public static final String DATE_FORMAT_YYMMDD = "yyMMdd";
	/** 年月ﾌｫｰﾏｯﾄ yyyy/MM */
	public static final String YM_FORMAT_YYYYMM_SLASH = "yyyy/MM";

	/**
	 * コンストラクタ
	 */
	private DateUtils() {
	}

	/**
	 * システム日付を取得します。
	 * @return システム日付
	 */
	public static Date getDate() {
		Date date = new Date();
		return date;
	}
	/**
	 * 指定された日付をyyyy/MM形式の文字列で返します。
	 * @return 指定された日付(yyyy/MM/dd)
	 */
	public static String getFormatDateWithYYYYMM() {
		if (getDate() == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(YM_FORMAT_YYYYMM_SLASH);
		String nowDateStr = dateFormat.format(getDate());

		return nowDateStr;
	}

	/**
	 * システム日付をyyyy/MM/dd形式の文字列で返します。
	 * @return システム日付(yyyy/MM/dd)
	 */
	public static String getSystemDateWithSlash() {

		return getFormatDateWithSlash(getDate());
	}

	/**
	 * 指定された日付をyyyy/MM/dd形式の文字列で返します。
	 * @return 指定された日付(yyyy/MM/dd)
	 */
	public static String getFormatDateWithSlash(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD_SLASH);
		String nowDateStr = dateFormat.format(date);

		return nowDateStr;
	}

	/**
	 * "yyyy/MM/dd"形式の文字列を日付に変換します。
	 * @param str
	 * @return 日付
	 * @throws ParseException
	 */
	public static Date toDateWithSlash(String str) {
		return toDate(str, DATE_FORMAT_YYYYMMDD_SLASH);
	}

	/**
	 * 文字列を日付に変換します。
	 * @param str
	 * @param format
	 * @return 日付
	 * @throws ParseException
	 */
	public static Date toDate(String str, String format) {
		if (StringUtils.isEmpty(str) || StringUtils.isEmpty(format)) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
		Date date = null;
		try {
			date = simpleDateFormat.parse(str);
		} catch (ParseException e) {
			// 何もしない
		}
		return date;
	}

	/**
	 * 日付の計算を行います。
	 *
	 * @param date 日付
	 * @param Year 年
	 * @param Month 月
	 * @param Day 日
	 * @return 計算された日にち
	 * @version 4.0.0
	 */
	public static Date addDate(Date date, int year, int month, int day) {

		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		if (year != 0) {
			calendar.add(Calendar.YEAR, year);
		}
		if (month != 0) {
			calendar.add(Calendar.MONTH, month);
		}
		if (day != 0) {
			calendar.add(Calendar.DATE, day);
		}

		return calendar.getTime();
	}

	/**
	 * 日付に年を加算します。
	 * @param date
	 * @param year 加算する年
	 * @return 計算された日にち
	 */
	public static Date addYear(Date date, int year) {
		return addDate(date, year, 0, 0);
	}

	/**
	 * 日付に月を加算します。
	 * @param date
	 * @param month 加算する月
	 * @return 計算された日にち
	 */
	public static Date addMonth(Date date, int month) {
		return addDate(date, 0, month, 0);
	}

	/**
	 * 日付に日を加算します。
	 * @param date
	 * @param day 加算する日
	 * @return 計算された日にち
	 */
	public static Date addDay(Date date, int day) {
		return addDate(date, 0, 0, day);
	}

	/**
	 * 日付１、日付２の比較を行います。<BR>
	 *  例)<BR>
	 *  　日付１<日付２　＝　-1<BR>
	 *  　日付１=日付２　＝　 0<BR>
	 *  　日付１>日付２　＝　 1<BR>
	 *
	 * @return 比較結果
	 * @param value1 日付１
	 * @param value2 日付２
	 **/
	public static int compareTo(String value1, String value2) {

		Date date1 = toDateWithSlash(value1);
		Date date2 = toDateWithSlash(value2);

		return compareTo(date1, date2);
	}

	/**
	 * 日付１、日付２の比較を行います。<BR>
	 *  例)<BR>
	 *  　日付１<日付２　＝　-1<BR>
	 *  　日付１=日付２　＝　 0<BR>
	 *  　日付１>日付２　＝　 1<BR>
	 *
	 * @return 比較結果
	 * @param value1 日付１
	 * @param value2 日付２
	 **/
	public static int compareTo(Date value1, Date value2) {

		Calendar cal = Calendar.getInstance();
		cal.set(0, 1, 1);

		Date date1 = value1;
		Date date2 = value2;

		if (date1 == null) {
			date1 = cal.getTime();
		}
		if (date2 == null) {
			date2 = cal.getTime();
		}

		return date1.compareTo(date2);
	}
}
