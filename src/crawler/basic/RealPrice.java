package crawler.basic;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;

/**
 * 練習： 實價登錄資料取得
 * 
 * 重點 1. 練習找出實價登錄公開資料源 2. zip 格式資料如何取出指定檔案
 * 
 * @author Abola Lee
 *
 */
public class RealPrice {
	public static void main(String[] args) {
		String day = "105/04/07";
		String uri = "http://www.tpex.org.tw/web/stock/margin_trading/margin_balance/margin_bal_result.php?l=zh-tw&d="
				+ day;

		// // 印出整份 XML 資料
		// System.out.println(CrawlerPack.start()
		//
		// // 參數設定
		// // .addCookie("key","value") // 設定cookie
		// // .setRemoteEncoding("big5")// 設定遠端資料文件編碼
		//
		// // 選擇資料格式 (三選一)
		// .getFromJson(uri)
		// // .getFromHtml(uri)
		// // .getFromXml(uri)
		//
		// // 這兒開始是 Jsoup Document 物件操作
		// // .select(".css .selector ")
		// // .select("data")
		//
		// .toString());

		// // reportDate: "105/03/30",
		// // iTotalRecords: 602,
		// // aaData: [],
		// // tfootData_one: [],
		// // tfootData_two: []

//		System.out.println("day : " + CrawlerPack.start().getFromJson(uri).select("reportDate"));
//		System.out.println("nrow : " + CrawlerPack.start().getFromJson(uri).select("iTotalRecords"));
//		System.out.println("totalbyShares : " + CrawlerPack.start().getFromJson(uri).select("tfootData_one"));
//		System.out.println("totalbyDolars : " + CrawlerPack.start().getFromJson(uri).select("tfootData_two"));

		String dayoutput = CrawlerPack.start().getFromJson(uri).select("reportDate").text();
		String nrowoutput = CrawlerPack.start().getFromJson(uri).select("iTotalRecords").text();
		int k = Integer.parseInt(nrowoutput);
		
		Elements elems = CrawlerPack.start().getFromJson(uri).select("aaData");
		String dataoutput = "代號,名稱,前資餘額(張),資買,資賣," + "現償,資餘額,資屬證金,資使用率(%),資限額," + "前券餘額(張),券賣,券買,券償,券餘額,券屬證金,"
				+ "券使用率(%),券限額,資券相抵(張),備註\n";// 19 ncol
		String data = null;
		// 遂筆處理
		for (int j = 0; j < k; j++) {
			// System.out.println(data.get(i).toString());
			String dataoutput1 = String.join(",", elems.get(j).select("array:eq(" + 1 + ")").text(),
					elems.get(j).select("array:eq(" + 2 + ")").text(),
					elems.get(j).select("array:eq(" + 3 + ")").text(),
					elems.get(j).select("array:eq(" + 4 + ")").text(),
					elems.get(j).select("array:eq(" + 5 + ")").text(),
					elems.get(j).select("array:eq(" + 6 + ")").text(),
					elems.get(j).select("array:eq(" + 7 + ")").text(),
					elems.get(j).select("array:eq(" + 8 + ")").text(),
					elems.get(j).select("array:eq(" + 9 + ")").text(),
					elems.get(j).select("array:eq(" + 10 + ")").text(),
					elems.get(j).select("array:eq(" + 11 + ")").text(),
					elems.get(j).select("array:eq(" + 12 + ")").text(),
					elems.get(j).select("array:eq(" + 13 + ")").text(),
					elems.get(j).select("array:eq(" + 14 + ")").text(),
					elems.get(j).select("array:eq(" + 15 + ")").text(),
					elems.get(j).select("array:eq(" + 16 + ")").text(),
					elems.get(j).select("array:eq(" + 17 + ")").text(),
					elems.get(j).select("array:eq(" + 18 + ")").text(),
					elems.get(j).select("array:eq(" + 19 + ")").text(),
					elems.get(j).select("array:eq(" + 20 + ")").text());
			// System.out.println(data);
			dataoutput += dataoutput1 + "\n";
		}
		// // reportDate: "105/03/30",
		// // iTotalRecords: 602,
		// // aaData: [],
		// // tfootData_one: [],
		// // tfootData_two: []
		Elements elems1 = CrawlerPack.start().getFromJson(uri).select("tfootData_one");
//		String totalbySharesoutput = "前資餘額(張),資買,資賣,現償,資餘額,前券餘額(張),券賣,券買,券償,券餘額";// 10 ncol
			for (int j = 1; j <= 10; j++) {
System.out.println(elems1.get(j).toString());
//				String totalbySharesoutput1 = String.join(",", elems1.get(j).select("array:eq(" + 1 + ")").text(),
//						elems1.get(j).select("array:eq(" + 2 + ")").text(),
//						elems1.get(j).select("array:eq(" + 3 + ")").text(),
//						elems1.get(j).select("array:eq(" + 4 + ")").text(),
//						elems1.get(j).select("array:eq(" + 5 + ")").text(),
//						elems1.get(j).select("array:eq(" + 6 + ")").text(),
//						elems1.get(j).select("array:eq(" + 7 + ")").text(),
//						elems1.get(j).select("array:eq(" + 8 + ")").text(),
//						elems1.get(j).select("array:eq(" + 9 + ")").text(),
//						elems1.get(j).select("array:eq(" + 10 + ")").text());
//				// System.out.println(data);
//				totalbySharesoutput += totalbySharesoutput1 + "\n";
//			}
//		Elements elems11 = CrawlerPack.start().getFromJson(uri).select("tfootData_two");
//		String totalbyDolarsoutput = "前資餘額(張),資買,資賣,現償,資餘額";// 5 ncol
//		for (int j = 1; j <= 10; j++) {
//			// System.out.println(data.get(i).toString());
//			String dataoutput1 = String.join(",", elems11.get(j).select("array:eq(" + 1 + ")").text(),
//					elems11.get(j).select("array:eq(" + 2 + ")").text(),
//					elems11.get(j).select("array:eq(" + 3 + ")").text(),
//					elems11.get(j).select("array:eq(" + 4 + ")").text(),
//					elems11.get(j).select("array:eq(" + 5 + ")").text());
//			// System.out.println(data);
//			dataoutput += dataoutput1 + "\n";
		}
		System.out.println("day : " + dayoutput);
		System.out.println("nrow : " + nrowoutput);
//		System.out.println("data" + dataoutput);
//		System.out.println("totalbyShares : " + totalbySharesoutput);
//		System.out.println("totalbyDolars : " + totalbyDolarsoutput);
		System.out.println("FINISHED");
		// final static String beautyMainPage =
		// "https://www.ptt.cc/bbs/Beauty/index.html";
		// final static String beautyIndexPage =
		// "https://www.ptt.cc/bbs/Beauty/index%s.html";
		// // 取得最後幾篇的文章數量
		// static Integer loadLastPosts = 20;
		//
		// String prevPage =
		// CrawlerPack.start()
		// .getFromHtml(beautyMainPage) // 遠端資料格式為 HTML
		// .select(".action-bar .pull-right > a") // 取得右上角『前一頁』的內容
		// .get(1).attr("href")
		// .replaceAll("/bbs/Beauty/index([0-9]+).html", "$1");

	}

}
