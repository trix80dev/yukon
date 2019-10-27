package yukon.util;

import java.util.Random;

public class Crypto {

	public static String generateRandomString(int length)
	{
		String str = "";
		
		while(str.length() < length)
		{
			char[] charz = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', '|' };
			
			str += charz[new Random().nextInt(charz.length)];
		}
		
		return str;
	}
	
	public static String generateRandomKey()
	{
		return generateRandomString(7 + new Random().nextInt(3));
	}
	
}
