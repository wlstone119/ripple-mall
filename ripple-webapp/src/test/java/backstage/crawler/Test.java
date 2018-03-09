package backstage.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.stone.ripple.util.tool.StringUtil;

public class Test {

	public static void main(String[] args) throws IOException {
	    
	    
	    
//	    try{
//	        String a = null;
//	        a.toString();
//	    }catch(Exception e){
//	        System.out.println(JSON.toJSONString(e));
//	    }
//	    
	    
//	    String[] str = {"com.weidai.clpublic.dao.bean.loan.LoanRecordBean","(com.weidai.clpublic.dao.bean.loan.LoanRecordBean"};
//	    System.out.println(JSON.toJSONString(str));
//	    
//	    int i= ("com.stone.ripple.dal.pojo.music.SongDoExample.update".replace(".", ",").split(",").length)-1;
//	    System.out.println(i);
//	    System.out.println("com.stone.ripple.dal.pojo.music.SongDoExample.update".replace(".", ",").split(",")[i]);
//	    
//	    System.out.println("com.stone.ripple.dal.pojo.music.SongDoExample.update".lastIndexOf("."));
//	    String strs = "com.stone.ripple.dal.pojo.music.SongDoExample.update";
//	    String str2 = strs.substring(0, strs.lastIndexOf("."));
//	    System.out.println(str2.substring(str2.lastIndexOf(".")+1,str2.length()));
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//		System.out.println(StringUtil.stringify(context));
//		// System.out.println(context.getBean("baiduMusicCrawler"));
//
//		Document songs = connectUrl("http://music.163.com/playlist?id=418903732");
//		File file = new File("/Users/stone/text.txt");
//		FileOutputStream out = new FileOutputStream(file);
//		out.write(songs.toString().getBytes("gbk"));
	}

	private static Document connectUrl(String url) throws IOException {
		return Jsoup.connect(url).userAgent("Mozilla").get();
	}
}
