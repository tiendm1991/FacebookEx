package tiendm.jsoup;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {
	public static <E> List<E> getCommonTwoList(List<E> ls1, List<E> ls2){
		List<E> lsReturn = new ArrayList<E>();
		for(E e1 : ls1){
			if(ls2.contains(e1)) lsReturn.add(e1);
		}
		return lsReturn;
	}
}
