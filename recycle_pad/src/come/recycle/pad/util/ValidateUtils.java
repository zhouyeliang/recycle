package come.recycle.pad.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * У�鹤����
 * 
 * @author duhanchen
 * 
 */
public class ValidateUtils {

	/**
	 * У���ֻ��
	 * 
	 * @param str
	 *            ��У���ַ�
	 * @return
	 */
	public static boolean validatePhoneNumber(String str) {

		String telecomRegx = "^(133)[0-9]{8}|(153)[0-9]{8}|(18)[019][0-9]{8}|(177)[0-9]{8}$";
		String unicomRegx = "^(13)[012][0-9]{8}|(15)[56][0-9]{8}|(18)[56][0-9]{8}|(145)[0-9]{8}|(176)[0-9]{8}$";
		String mobileRegx = "^(134)[0-8][0-9]{7}|(13)[5-9][0-9]{8}|(147)[0-9]{8}|(15)[012789][0-9]{8}|(18)[23478][0-9]{8}|(178)[0-9]{8}$";
		String virtualRegx = "^(170)[0-9]{8}$";

		if (validateRegx(telecomRegx, str) || validateRegx(unicomRegx, str)
				|| validateRegx(mobileRegx, str)
				|| validateRegx(virtualRegx, str)) {
			return true;
		}

		return false;
	}

	/**
	 * У��������ĸ�����ֵ����
	 * 
	 * @param str
	 *            ��У���ַ�
	 * @param length
	 *            ָ��������ʽ����ĸ�����ִ�����
	 * @return
	 */
	public static boolean validateAllOfAlphabetsAndNumbers(String str,
			int length) {

		Pattern pattern = Pattern.compile("^[A-Z a-z 0-9]{" + length + "}$");

		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}

	/**
	 * У��ָ�����ȵ�����
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static boolean validateNumbers(String str, int length) {

		Pattern pattern = Pattern.compile("^\\d{" + length + "}$");

		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}

	/**
	 * ͨ�õ�������ʽУ��
	 * 
	 * @param regx
	 *            ������ʽ
	 * @param str
	 *            ��У���ַ�
	 * @return
	 */
	public static boolean validateRegx(String regx, String str) {

		Pattern pattern = Pattern.compile(regx);

		Matcher matcher = pattern.matcher(str);

		return matcher.matches();
	}
}
