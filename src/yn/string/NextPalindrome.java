package yn.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class NextPalindrome {
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int totalCases = Integer.parseInt(br.readLine());
		for (int i = 0; i < totalCases; i++) {
			System.out.println(getNextPalindrome(br.readLine()));
		}
	}

	private static String getNextPalindrome(String str) {
		int len = str.length();
		if (len == 1 && !"9".equals(str)) {
			return addOne(str);
		}
		boolean isEven = len % 2 == 0;
		String firstHalf = str.substring(0, len / 2);
		String secondHalf = isEven ? str.substring(len / 2) : str
				.substring(len / 2 + 1);
		String mid = isEven ? "" : str.substring(len / 2, len / 2 + 1);
		int i = firstHalf.length() - 1;
		int j = 0;
		for (; j < len / 2; j++, i--) {
			if (firstHalf.charAt(i) > secondHalf.charAt(j)) {
				return firstHalf + mid + reverse(firstHalf);
			} else if (firstHalf.charAt(i) < secondHalf.charAt(j)) {
				if (!isEven) {
					if ("9".equals(mid)) {
						firstHalf = addOne(firstHalf);
						return firstHalf + "0" + reverse(firstHalf);
					} else {
						return firstHalf + addOne(mid) + reverse(firstHalf);
					}
				}
				firstHalf = addOne(firstHalf);
				return firstHalf + reverse(firstHalf);
			}
		}
		return getNextPalindrome(addOne(str));
	}

	private static String addOne(String s) {
		char[] c = s.toCharArray();
		for (int i = c.length - 1; i > -1; i--) {
			if (c[i] == '9') {
				c[i] = '0';
			} else {
				c[i]++;
				break;
			}
		}
		return c[0] == '0' ? "1" + new String(c) : new String(c);
	}

	private static String reverse(String s) {
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length / 2; i++) {
			char t = c[c.length - i - 1];
			c[c.length - i - 1] = c[i];
			c[i] = t;
		}
		return new String(c);
	}
}