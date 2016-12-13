package fun;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class reg {

	public static String getBack(String html, String regex){

		Pattern patt = Pattern.compile(regex);
		Matcher match = patt.matcher(html);

		while (match.find()) {

			return match.group(1);

		}
		return "";
	}

	public static ArrayList<String> getBackArray(String html, String regex){

		Pattern patt = Pattern.compile(regex);
		Matcher match = patt.matcher(html);

		ArrayList<String> mylist = new ArrayList<>();

		while (match.find()) {

			mylist.add(match.group(1));

		}
		return mylist;
	}

}
