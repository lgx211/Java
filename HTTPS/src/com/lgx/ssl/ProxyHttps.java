package com.lgx.ssl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLDecoder;

/*
 * 经过代理服务器后，由代理服务器走HTTPS。
 */
public class ProxyHttps {
	public static void main(String[] args) throws Throwable {

		// HTTPS详细过程
		System.setProperty("javax.net.debug", "all");

		String xmlServerURL = "https://www.baidu.com/";
		URL urlXMLServer = new URL(xmlServerURL);
		// 我自己的代理服务器
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.31.219.169", 808));
		HttpURLConnection httpsURLConnection = (HttpURLConnection) urlXMLServer.openConnection(proxy);

		httpsURLConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpsURLConnection.setDoInput(true);
		httpsURLConnection.setDoOutput(true);
		httpsURLConnection.setConnectTimeout(300000);
		httpsURLConnection.setUseCaches(false);
		httpsURLConnection.setRequestMethod("POST");
		httpsURLConnection.connect();

		PrintWriter out = new PrintWriter(httpsURLConnection.getOutputStream());
		// 传参数
		out.print("777");
		out.flush();
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));

		String result = "";
		String content = "";
		while ((content = in.readLine()) != null) {
			result += content;
		}

		String line;
		String respXML = "";
		while ((line = in.readLine()) != null) {
			respXML += line;
		}
		in.close();

		System.out.println(("getDataFromServer:" + result));
	}

}
