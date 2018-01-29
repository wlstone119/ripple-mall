package backstage.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.stone.ripple.util.tool.StringUtil;

public class Test {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		System.out.println(StringUtil.stringify(context));
		// System.out.println(context.getBean("baiduMusicCrawler"));

		Document songs = connectUrl("http://music.163.com/playlist?id=418903732");
		File file = new File("/Users/stone/text.txt");
		FileOutputStream out = new FileOutputStream(file);
		out.write(songs.toString().getBytes("gbk"));
	}

	private static Document connectUrl(String url) throws IOException {
		return Jsoup.connect(url).userAgent("Mozilla").get();
	}
}
