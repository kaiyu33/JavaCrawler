package MyJavaCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Rset {

	public static void main(String[] args) throws IOException {
		for(int i=1;i<=24;i++){
			System.out.println("D"+i+"<-sapply(strsplit(csvv[i],\";\"),\"[\","+i+")");
		}
		for(int i=1;i<=24;i++){
			System.out.print("D"+i+",");
		}
	}

}
