package com.lgx.ssl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/*
 * 经过代理服务器后，由代理服务器走HTTPS。
 */
public class IpHttps {
	public static void main(String[] args) throws Throwable {

		// HTTPS详细过程
		System.setProperty("javax.net.debug", "all");

		String xmlServerURL = "https://115.239.211.112/";

		URL urlXMLServer = new URL(xmlServerURL);
		// 我自己的代理服务器
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.31.219.169", 808));
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlXMLServer.openConnection(proxy);

		httpsURLConnection.setHostnameVerifier(new NullHostnameVerifier());

		httpsURLConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpsURLConnection.setDoInput(true);
		httpsURLConnection.setDoOutput(true);
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

	private static class NullHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

}
