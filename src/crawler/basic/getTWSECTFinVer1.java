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


public class getTWSECTFinVer1 {
	public static void main(String[] args) {
		try {
			TWSECT(500);
			TWSECTtotal(500);
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
		now.add(Calendar.DAY_OF_YEAR, -daynum);

		String getday = formatter.format(now.getTime());
		String sub1 = getday.substring(0, 4);
		String sub2 = getday.substring(4, getday.length());
		int yaer = (Integer.parseInt(sub1) - 1911);
		String getTWday = yaer + sub2;
		return getTWday;
	}

	public static String ADdaybefore(int daynum) {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		now.add(Calendar.DAY_OF_YEAR, -daynum);
		String getday = formatter.format(now.getTime());
		String sub1 = getday.substring(0, 4);
		String sub2 = getday.substring(4, getday.length());
		int yaer = Integer.parseInt(sub1);
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
			Elements decideelems = CrawlerPack.start().setRemoteEncoding("UTF-8").getFromHtml(uri)
					.select("#tbl-containerx > table:nth-child(4) > thead > tr:nth-child(1) > td > span");
			// System.out.println(decideelems.toString());
			String decide1 = decideelems.toString().substring(6, 9);
			String decide2 = decideelems.toString().substring(10, 12);
			String decide3 = decideelems.toString().substring(13, 15);
			String decide = decide1 + "/" + decide2 + "/" + decide3;
			boolean Equal = decide.substring(0, 9).equals(TWdaybefore(daynum).substring(0, 9));
			// System.out.println(Equal =decide.substring(0,9).equals(TWdaybefore(daynum).substring(0,9)));
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
					//// method:4 效能最好
					for (Element elems2 : elems) {
						String dataoutput1 = String.join(",", elems2.select("td:eq(" + 0 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 1 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 2 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 3 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 4 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 5 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 6 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 7 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 8 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 9 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 10 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 11 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 12 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 13 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 14 + ")").text().replace(",", ""),
								elems2.select("td:eq(" + 15 + ")").text().replace(",", "")//
						);
//						System.out.println(dataoutput1);
						bw.write(dataoutput1);
						bw.newLine();
						// dataoutput += dataoutput1 + "\n";
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

				Elements decideelems = CrawlerPack.start().setRemoteEncoding("UTF-8").getFromHtml(uri)
						.select("#tbl-containerx > table:nth-child(3) > thead > tr:nth-child(1) > td > span");
				// System.out.println(decideelems.toString());
				String decide1 = decideelems.toString().substring(6, 9);
				String decide2 = decideelems.toString().substring(10, 12);
				String decide3 = decideelems.toString().substring(13, 15);
				String decide = decide1 + "/" + decide2 + "/" + decide3;
				boolean Equal = decide.substring(0, 9).equals(TWdaybefore(daynum).substring(0, 9));
				// System.out.println(Equal =decide.substring(0,9).equals(TWdaybefore(daynum).substring(0,9)));
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
