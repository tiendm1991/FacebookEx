package tiendm.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tiendm.model.User;
import tiendm.util.CollectionUtil;
import tiendm.util.FileUtil;
import tiendm.util.StringUtil;

public class FacebookUtil {
	public static void main(String[] args) {
		try {
//			List<User> ls = crawlWithLogin("https://www.facebook.com/login.php?login_attempt=1", 
//					"https://www.facebook.com/caothu.bk",
//					"caothubk11291@gmail.com", "MANHTIEN26908711");
			
			List<User> lsMutual = getMutualFriend("C:\\Users\\TienDM\\Desktop\\fb\\0.html",
													"C:\\Users\\TienDM\\Desktop\\fb\\2.html");
			int i=0;
			Collections.sort(lsMutual);
			for (User user : lsMutual) {
				System.out.println((++i) + ": "+user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<User> getMutualFriend(String path1, String path2) throws IOException{
		List<User> lsUser1 = getAllFriendFB(path1);
		List<User> lsUser2 = getAllFriendFB(path2);
		Collections.sort(lsUser1);
		Collections.sort(lsUser2);
//		List<User> lsMutual = new ArrayList<>(lsUser1);
//		lsMutual.retainAll(lsUser2);
		List<User> lsMutual = CollectionUtil.getCommonTwoList(lsUser1, lsUser2);
		return lsMutual;
	}
		
	public static List<User> getAllFriendFB(String path) throws IOException{
		String strHtml = FileUtil.readFile(path);
		Document doc = Jsoup.parse(strHtml);
		List<User> ls = new ArrayList<>();
		Elements elements = doc.getElementsByClass("uiProfileBlockContent");
		for(Element e : elements){
			Element friend = e.select("a").first();
			User user = new User(StringUtil.findId(friend.attr("data-hovercard")), friend.text());
			ls.add(user);
		}
		return ls;
	}
	
	
	public static List<User> crawlWithLogin(String urlLogin, String url, String user, String pass) throws IOException{
		String user_agent = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
		Response login = Jsoup.connect(urlLogin)
							.userAgent(user_agent)
//				 			.data("ctl00$cLogIn1$tb_cLogIn_User", user).data("ctl00$cLogIn1$tb_cLogIn_Pass", pass)
				 			.data("email", user).data("pass", pass)
				 			.method(Method.POST)
				 			.userAgent(user_agent).execute();
		Document document = login.parse();
		System.out.println(document);
		Document doc = Jsoup.connect(url).cookies(login.cookies()).get();
		List<User> ls = new ArrayList<>();
		Elements elements = doc.getElementsByTag("a");
		return ls;
	}
	
	
}
