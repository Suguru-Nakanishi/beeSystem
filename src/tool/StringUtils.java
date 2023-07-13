package tool;

/**
 * 文字列操作クラス
 */
public class StringUtils {

	/**
	 * インスタンス化させない
	 */
	private StringUtils() {
	}

	/**
	 * 文字列が空文字かnullか確認します。
	 * @param str
	 * @return 空文字かnullならtrue
	 */
	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str));
	}

	/**
	 * 文字列が空文字でないか確認します。
	 * @param str
	 * @return 空文字でなければtrue
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 文字数を取得。
	 * @return 文字数
	 * @param src 対象文字列
	 */
	public static int length(String src) {

		if (src != null) {
			return src.length();

		} else {
			return 0;
		}
	}
}
