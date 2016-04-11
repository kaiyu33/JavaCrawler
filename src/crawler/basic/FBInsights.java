package crawler.basic;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;

/**
 * 資料探索練習 Facebook Graph Api Search
 * 
 * 重點 1. 利用Graph Api調整出需要的資料 2. 取得一組Access Token (long term token) 3.
 * 試著用你會的方式，先行探索資料
 * 
 * @author Abola Lee
 *
 */
public class FBInsights {

	public static void main(String[] args) {

		// 遠端資料路徑
		String uri = "https://graph.facebook.com/v2.5"
				+ "/search?q=%E9%9D%A0%E5%8C%97&type=page&fields=name,id,likes,talking_about_count" + "&access_token="
				+ "EAATBSeyVhucBACG5mwm7zQXjsFJl0lj3venV6nnbhwr4rmihHpFf4zUBl3ctyiYZCjx0Yn0FqzkFVqBUKSOMLAdzYXbXZAuelQjsvRxMNZAiNqmZAQHuQ511RwyaZAc6OP5y8Q85Llh8aIaZA02rGdiF3gYjAU8WpP7N23jLyDowZDZD";
		System.out.println(uri);
		// Jsoup select 後回傳的是 Elements 物件

		String output = "id,按讚數,名稱,討論人數\n";

		// 遂筆處理
		try {
			for (int i = 0; i < 23; i++) {
				// Jsoup select 後回傳的是 Elements 物件
				Elements elems = CrawlerPack.start().getFromJson(uri).select("data");
				for (Element data : elems) {
					String id = data.select("id").text();
					String likes = data.select("likes").text();
					String name = data.select("name").text();
					String talking_about_count = data.select("talking_about_count").text();

					output += id + "," + likes + ",\"" + name + "\"," + talking_about_count + "\n";
				}
				Elements elemsEnd = CrawlerPack.start().getFromJson(uri).select("paging>next");
				System.out.println(elemsEnd.text());
				uri = elemsEnd.text();
//				try{Thread.sleep(3000);}catch(Exception e){}
			}
		} catch (IndexOutOfBoundsException e) {
			// break;
		}
		System.out.println(output);

	}
}
