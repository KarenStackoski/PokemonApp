package main;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

	public static String removeAccents(String input) {
		// Normalize the input string to decompose accented characters into base characters and combining diacritical marks
		String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

		// Remove all combining diacritical marks using regex
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(normalized).replaceAll("");

	}
}
