package basics.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** 
  * @description
  * @author   stone
  * @date     2018年1月4日
  */
public class Java8 {
	
	public static void main(String[] args){
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		System.out.println(names.toString());
//		Collections.sort(names, (String a, String b) -> {
//			return a.compareTo(b);
//		});
		System.out.println(names.toString());
	}
	

}
