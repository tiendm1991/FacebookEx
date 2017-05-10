package tiendm.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	public static String readFile(String path) throws IOException{
		StringBuilder str = new StringBuilder("");
		BufferedReader br = null; 
		try {
			br = new BufferedReader(new FileReader(path));
			String newStr = "";
			while ((newStr = br.readLine()) != null) {
				str.append(newStr);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			br.close();
		}
		return str.toString();
	}
}
