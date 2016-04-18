package crawler.basic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.github.abola.crawler.CrawlerPack;


public class getTWSECT {
	public static void main(String[] args) {
		// for (int j = 94; j <= 104; j++) {// 從94開始有資料
		int j = 94;
		try {
			TWSECT(10);
//			TWSECTtotal(10);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// String changeFileName = j + "YearCoupon";
			// System.out.println(changeFileName);
			// try {
			// changeCode(changeFileName, "UTF-8", "BIG5");
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
		// }

	}

	public static void TWday() {
		Date myDate = new Date();
		int thisYear = myDate.getYear() + 1900;// thisYear = 2003
		int thisMonth = myDate.getMonth() + 1;// thisMonth = 5
		String Month = String.format("%02d", thisMonth);
		int thisDate = myDate.getDate();// thisDate = 30
		String Date = String.format("%02d", thisDate);
		System.out.println(thisYear - 1911 + "/" + Month + "/" + Date);
	}

	public static void TWday(int year, int month, int day) {
		String Month = String.format("%02d", month);
		String Date = String.format("%02d", day);
		System.out.println(year - 1911 + "/" + Month + "/" + Date);
	}

	public static String TWdaybefore(int daynum) {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		// "E yyyy.MM.dd"=星期日 2016.04.10
		// System.out.println(formatter.format(now.getTime()));
		now.add(Calendar.DAY_OF_YEAR, -daynum);
		// System.out.println(formatter.format(now.getTime()));

		String getday = formatter.format(now.getTime());
		// 包含起始值，不包含終始值
		String sub1 = getday.substring(0, 4);
		String sub2 = getday.substring(4, getday.length());
		int yaer = (Integer.parseInt(sub1) - 1911);
		// System.out.println(getday.length());
		// System.out.println(yaer + sub2);
		String getTWday = yaer + sub2;
		return getTWday;
	}

	public static String ADdaybefore(int daynum) {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		// "E yyyy.MM.dd"=星期日 2016.04.10
		// System.out.println(formatter.format(now.getTime()));
		now.add(Calendar.DAY_OF_YEAR, -daynum);
		// System.out.println(formatter.format(now.getTime()));
		String getday = formatter.format(now.getTime());
		// 包含起始值，不包含終始值
		String sub1 = getday.substring(0, 4);
		String sub2 = getday.substring(4, getday.length());
		int yaer = Integer.parseInt(sub1);
		// System.out.println(getday.length());
		// System.out.println(yaer + sub2);
		String getTWday = yaer + sub2;
		return getTWday;
	}

	public static void TWSECT(int befredaynum) throws Exception {

		// TWday();
		// TWday(2016, 4, 10);
		// TWdaybefore(50);// 距今五十天
		for (int daynum = 0; daynum < befredaynum; daynum++) {
			// int daynum = 3;
			// String day = "105/04/07";
			String uri = "http://www.twse.com.tw/device/ch/trading/exchange/MI_MARGN/MI_MARGN.php?input_date="
					+ TWdaybefore(daynum) + "&selectType=ALL&login_btn=%20%E6%9F%A5%E8%A9%A2%20";
System.out.println(uri);
			// 印出整份 XML 資料
			// System.out.println(CrawlerPack.start()
			//
			// // 參數設定
			// // .addCookie("key","value") // 設定cookie
			// // .setRemoteEncoding("big5")// 設定遠端資料文件編碼
			//
			// // 選擇資料格式 (三選一)
			//// .getFromJson(uri)
			// .getFromHtml(uri)
			//// .getFromXml(uri)
			//
			// // 這兒開始是 Jsoup Document 物件操作
			// // .select(".css .selector ")
			// // .select("data")
			//
			// .toString());

			// System.out.println("day : " +
			// CrawlerPack.start().getFromHtml(uri).select("reportDate"));
			// String dayoutput =
			// CrawlerPack.start().getFromJson(uri).select("reportDate").text();

			// int k = Integer.parseInt(nrowoutput);

			// .select("td:matchesOwn(^[\\+\\-]?([0-9]{1,3},)*[0-9]{1,3}(\\.[0-9]+)*$)");
			// "td > table tr:gt(0)")
			// #tbl-containerx > table:nth-child(4) > tbody > tr:nth-child(1) >
			// td:nth-child(1)

			// #tbl-containerx > table:nth-child(4) > thead > tr:nth-child(1) >
			// td >
			// span
			Elements decideelems = CrawlerPack.start().setRemoteEncoding("UTF-8").getFromHtml(uri)
					.select("#tbl-containerx > table:nth-child(4) > thead > tr:nth-child(1) > td > span");
			// System.out.println(decideelems.toString());
			String decide1 = decideelems.toString().substring(6, 9);
			String decide2 = decideelems.toString().substring(10, 12);
			String decide3 = decideelems.toString().substring(13, 15);
			// System.out.println(decide1);
			// System.out.println(decide2);
			// System.out.println(decide3);
			String decide = decide1 + "/" + decide2 + "/" + decide3;
			// System.out.println(TWdaybefore(daynum));
			// System.out.println(decide);
			boolean Equal = decide.substring(0, 9).equals(TWdaybefore(daynum).substring(0, 9));
			// System.out.println(Equal =
			// decide.substring(0,9).equals(TWdaybefore(daynum).substring(0,9)));
			if (Equal) {
				System.out.println("OK");

				Elements elems = CrawlerPack.start().setRemoteEncoding("UTF-8").getFromHtml(uri)
						.select("#tbl-containerx > table:nth-child(4) > tbody > tr");
				// System.out.println(elems.get(0).select("td:eq("+2+")").text());
				String dataoutput = "代號,名稱,買進,賣出,現金償還,前日餘額,今日餘額,限額,買進,賣出,現券償還,前日餘額,今日餘額,限額,資券互抵,註記\n";// 1before6ncol

				File file = new File("D:/EXdata/CreditTransactions/TWSECT" + ADdaybefore(daynum) + ".csv");
				file.createNewFile();
				FileWriter fw = new FileWriter(file, false);
				// TRUE:繼續增加文字
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(dataoutput);

				try {
					// 遂筆處理
					//// method:5 4改進代碼取得問題 0050變成50

					for (Element elems2 : elems) {
						String dataoutput1 = "\'"+
							elems2.select("td:eq(" + 0 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 1 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 2 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 3 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 4 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 5 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 6 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 7 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 8 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 9 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 10 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 11 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 12 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 13 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 14 + ")").text().replace(",", "")+","+
								elems2.select("td:eq(" + 15 + ")").text().replace(",", "")//
								;
//						System.out.println(dataoutput1);
						bw.write(dataoutput1);
						bw.newLine();
						// dataoutput += dataoutput1 + "\n";
					}
				//// method:4 效能最好
//					for (Element elems2 : elems) {
//						String dataoutput1 = String.join(",", "\"",
//								elems2.select("td:eq(" + 0 + ")").text().replace(",", ""),"\"",
//								elems2.select("td:eq(" + 1 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 2 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 3 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 4 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 5 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 6 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 7 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 8 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 9 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 10 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 11 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 12 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 13 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 14 + ")").text().replace(",", ""),
//								elems2.select("td:eq(" + 15 + ")").text().replace(",", "")//
//						);
////						System.out.println(dataoutput1);
//						bw.write(dataoutput1);
//						bw.newLine();
//						// dataoutput += dataoutput1 + "\n";
//					}
					// 遂筆處理
					// for (int j = 0; j < 1000; j++) {
					// // System.out.println(data.get(i).toString());
					////// method:3 效能最好
					// Element elems2 = elems.get(j);
					//// System.out.println(elems2.select("td:eq(" + 1 +
					// ")").text().replace(",", ""));
					// String dataoutput1 =
					// String.join(",",elems2.select("td:eq("+0+")").text().replace(",",""),
//					elems2.select("td:eq("+1+")").text().replace(",",""),
					// elems2.select("td:eq("+2+")").text().replace(",", ""),
					// elems2.select("td:eq("+3+")").text().replace(",", ""),
					// elems2.select("td:eq("+4+")").text().replace(",", ""),
					// elems2.select("td:eq("+5+")").text().replace(",", ""),
					// elems2.select("td:eq("+6+")").text().replace(",", ""),
					// elems2.select("td:eq("+7+")").text().replace(",", ""),
					// elems2.select("td:eq("+8+")").text().replace(",", ""),
					// elems2.select("td:eq("+9+")").text().replace(",", ""),
					// elems2.select("td:eq("+10+")").text().replace(",", ""),
					// elems2.select("td:eq("+11+")").text().replace(",", ""),
					// elems2.select("td:eq("+12+")").text().replace(",", ""),
					// elems2.select("td:eq("+13+")").text().replace(",", ""),
					// elems2.select("td:eq("+14+")").text().replace(",", ""),
					// elems2.select("td:eq("+15+")").text().replace(",", "")//
					// );
					////// method:1
					//// String dataoutput1 = String.join(",",
					// elems.get(j).select("td:eq(" + 0 + ")").text(),
					//// elems.get(j).select("td:eq(" + 1 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 2 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 3 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 4 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 5 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 6 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 7 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 8 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 9 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 10 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 11 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 12 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 13 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 14 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 15 +
					// ")").text().replace(",", "")//
					//// );
					//// method:2 效能較差 程式碼較短
					//// String dataoutput1 = "String dataoutput1 = ";
					//// int colmax = 20;
					//// for (int i = 0; i <= colmax; i++) {
					//// dataoutput1 += "elems.get(j).select(\"td:eq(\"+" + i +
					// "+\")\").text().replace(\",\", \"\")";
					////
					//// if (i < colmax) {
					//// dataoutput1 += ",\n";
					//// } else if (i == colmax) {
					//// dataoutput1 += ";";
					//// }
					//// }
					//// System.out.println(dataoutput1);
					//
					// // String str2 = str1.replace('-',',');
					//
					// bw.write(dataoutput1);
					// bw.newLine();
					//
					// // System.out.println(data);
					// // dataoutput += dataoutput1 + "\n";
					// }
					// System.out.println("data" + dataoutput);
				} catch (IndexOutOfBoundsException e) {
					// break;
				} finally {
					try {
						bw.close();
						fw.close();
						System.out.println("FINISHED:TWSECT");
					} catch (IOException e) {
					}
				} // 完整寫入檔案
			} else {
				System.out.println("@#$%^&*");
			}
		}
	}

	public static void TWSECTtotal(int befredaynum) throws Exception {
		File file = new File("D:/EXdata/CreditTransactions/TWSECTtotal.csv");
		file.createNewFile();
		FileWriter fw = new FileWriter(file, false);
		// TRUE:繼續增加文字
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			String dataoutput = "日期,項目,買進,賣出,現金(券)償還,前日餘額,今日餘額\n";// 1before6ncol
			bw.write(dataoutput);
			// TWday();
			// TWday(2016, 4, 10);
			// TWdaybefore(50);// 距今五十天
			for (int daynum = 0; daynum < befredaynum; daynum++) {
				// int daynum = 3;
				// String day = "105/04/07";
				String uri = "http://www.twse.com.tw/device/ch/trading/exchange/MI_MARGN/MI_MARGN.php?input_date="
						+ TWdaybefore(daynum) + "&selectType=ALL&login_btn=%20%E6%9F%A5%E8%A9%A2%20";

				// 印出整份 XML 資料
				// System.out.println(CrawlerPack.start()
				//
				// // 參數設定
				// // .addCookie("key","value") // 設定cookie
				// // .setRemoteEncoding("big5")// 設定遠端資料文件編碼
				//
				// // 選擇資料格式 (三選一)
				//// .getFromJson(uri)
				// .getFromHtml(uri)
				//// .getFromXml(uri)
				//
				// // 這兒開始是 Jsoup Document 物件操作
				// // .select(".css .selector ")
				// // .select("data")
				//
				// .toString());

				// System.out.println("day : " +
				// CrawlerPack.start().getFromHtml(uri).select("reportDate"));
				// String dayoutput =
				// CrawlerPack.start().getFromJson(uri).select("reportDate").text();

				// int k = Integer.parseInt(nrowoutput);

				// .select("td:matchesOwn(^[\\+\\-]?([0-9]{1,3},)*[0-9]{1,3}(\\.[0-9]+)*$)");
				// "td > table tr:gt(0)")
				// #tbl-containerx > table:nth-child(4) > tbody >
				// tr:nth-child(1) >
				// td:nth-child(1)

				// #tbl-containerx > table:nth-child(4) > thead >
				// tr:nth-child(1) >
				// td >
				// span

				Elements decideelems = CrawlerPack.start().setRemoteEncoding("UTF-8").getFromHtml(uri)
						.select("#tbl-containerx > table:nth-child(3) > thead > tr:nth-child(1) > td > span");
				// System.out.println(decideelems.toString());
				String decide1 = decideelems.toString().substring(6, 9);
				String decide2 = decideelems.toString().substring(10, 12);
				String decide3 = decideelems.toString().substring(13, 15);
				// System.out.println(decide1);
				// System.out.println(decide2);
				// System.out.println(decide3);
				String decide = decide1 + "/" + decide2 + "/" + decide3;
				// System.out.println(TWdaybefore(daynum));
				// System.out.println(decide);
				boolean Equal = decide.substring(0, 9).equals(TWdaybefore(daynum).substring(0, 9));
				// System.out.println(Equal =
				// decide.substring(0,9).equals(TWdaybefore(daynum).substring(0,9)));
				if (Equal) {
					System.out.println("OK");

					Elements elems = CrawlerPack.start().setRemoteEncoding("UTF-8").getFromHtml(uri)
							.select("#tbl-containerx > table:nth-child(3) > tbody > tr");
							// System.out.println(elems.get(0).select("td:eq("+2+")").text());

					// 遂筆處理
					//// method:4 效能最好
					for (Element elems2 : elems) {
						String dataoutput1 = String.join(",", ADdaybefore(daynum),
								elems2.select("td:eq(" + 0 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 1 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 2 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 3 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 4 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 5 + ")").text().replace(",", "")//
						);
//						System.out.println(dataoutput1);
						bw.write(dataoutput1);
						bw.newLine();
						// dataoutput += dataoutput1 + "\n";
					}

					// 遂筆處理
					// for (int j = 0; j < 3; j++) {
					// // System.out.println(data.get(i).toString());
					////// method:1
					//// String dataoutput1 = String.join(",",
					// ADdaybefore(daynum),
					//// elems.get(j).select("td:eq(" + 0 + ")").text(),
					//// elems.get(j).select("td:eq(" + 1 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 2 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 3 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 4 +
					// ")").text().replace(",", ""),
					//// elems.get(j).select("td:eq(" + 5 +
					// ")").text().replace(",", "")//
					//// );
					////// method:3 效能最好
					// Element elems2 = elems.get(j);
					//// System.out.println(elems2.select("td:eq(" + 1 +
					// ")").text().replace(",", ""));
					// String dataoutput1 = String.join(",",ADdaybefore(daynum),
					// elems2.select("td:eq(" + 0 + ")").text(),
					// elems2.select("td:eq("+1+")").text().replace(",", ""),
					// elems2.select("td:eq("+2+")").text().replace(",", ""),
					// elems2.select("td:eq("+3+")").text().replace(",", ""),
					// elems2.select("td:eq("+4+")").text().replace(",", ""),
					// elems2.select("td:eq("+5+")").text().replace(",", "")//
					// );
					//// method:2 效能較差 程式碼較短
					//// String dataoutput1 = "String dataoutput1 = ";
					//// int colmax = 5;
					//// for (int i = 0; i <= colmax; i++) {
					//// dataoutput1 += "elems.get(j).select(\"td:eq(\"+" + i +
					// "+\")\").text().replace(\",\", \"\")";
					//// if (i < colmax) {
					//// dataoutput1 += ",\n";
					//// } else if (i == colmax) {
					//// dataoutput1 += ";";
					//// }
					//// }
					//// System.out.println(dataoutput1);
					// // String str2 = str1.replace('-',',');
					//
					// bw.write(dataoutput1);
					// bw.newLine();
					//
					// // System.out.println(data);
					// // dataoutput += dataoutput1 + "\n";
					// }
					// System.out.println("data" + dataoutput);

				} else {
					System.out.println("@#$%^&*");
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// break;
		} finally {
			try {
				bw.close();
				fw.close();
				System.out.println("FINISHED:TWSECT");
			} catch (IOException e) {
			}
		} // 完整寫入檔案
	}
}
