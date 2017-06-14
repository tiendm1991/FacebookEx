package tiendm.jsoup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	public static void main(String[] args) {
	}
	
	
	
	static Document getConnect(String url, Map<String, String> loginCookies) throws IOException {
	    org.jsoup.Connection connection = Jsoup.connect(url);
	    Document doc = connection.cookies(loginCookies).get();
	    return doc;
	}
	
	static Map<String,String> connect() throws IOException{
		Connection.Response res = Jsoup.connect("https://www.facebook.com/login.php")
			    .data("username", "meohenbk@gmail.com", "password", "269087")
			    .method(Method.POST)
			    .execute();
		Document doc = res.parse();
		Map<String, String> loginCookies = res.cookies();
		String sessionId = res.cookie("SESSIONID");
		return loginCookies;
	}
	
}
