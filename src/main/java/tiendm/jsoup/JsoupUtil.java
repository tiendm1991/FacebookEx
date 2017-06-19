package tiendm.jsoup;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupUtil {
	public static void main(String[] args) {
		String s = "<html> "
					+ "<body>"
						+ "<div> xbcasdmildvnkcvdljvnlkdfndnfirngm,dfmvldfnoi"
						+ "</div>"
						+ "<span> 1231646431646545645612313545456231545656152"
						+ "</span>"
					+ "</body>"
				+ "</html>";
		Element node = Jsoup.parse(s).getElementsByTag("span").first();
		String text = node.text();
		System.out.println(text);
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
