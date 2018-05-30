package login;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


public class Encde {
	
	public static String encode(String pinnecodat) throws UnsupportedEncodingException
	{
		byte[] bytes = pinnecodat.getBytes("UTF-8");
		return Base64.getEncoder().encodeToString(bytes);
	}
	
}
