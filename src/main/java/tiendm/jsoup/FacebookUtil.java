package tiendm.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
			List<User> lsMutual = getMutualFriend("C:\\Users\\TienDM\\Desktop\\fb\\0.html",
													"C:\\Users\\TienDM\\Desktop\\fb\\1.html");
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
}
