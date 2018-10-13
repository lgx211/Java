package com.lgx.https;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class SSLUtil {
	public String KEY_STORE_PASS = "111111";

	private SSLContext sslContext;

	public String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			// 连接
			URL realUrl = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();

			// 套接socket
			if (connection instanceof HttpsURLConnection) {
				((HttpsURLConnection) connection).setSSLSocketFactory(getSSLContext().getSocketFactory());
			}

			// 设置通用的请求属性
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
			// 设置接收数据的格式
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("OriIssrId", "C1030131001288");
			connection.setRequestProperty("MsgTP", "uops.010.000.01");
			// 设置维持长连接
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Accept-Encoding", "gzip");
			// 设置文件字符集:
			connection.setRequestProperty("Charset", "UTF-8");
			// 设置发送数据的格式
			connection.setRequestProperty("Content-Type", "application/json");
			// 设置不用缓存,注意设置请求方法为post不能用缓存
			connection.setUseCaches(false);
			// 设置传递方式
			connection.setRequestMethod("POST");

			// 开始连接请求
			connection.connect();

			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(connection.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();

			// 读取URL的响应，返回的流。如果有错误，返回错误的信息
			if (connection.getResponseCode() == 200) {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "UTF-8"));
			}

			// 转字符串
			String content = "";
			while ((content = in.readLine()) != null) {
				result += content;
			}

		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}

		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		System.out.println(("getDataFromServer:" + result));
		return result;
	}

	public SSLContext getSSLContext() throws Exception {
		long time1 = System.currentTimeMillis();

		if (sslContext == null) {
			try {
				// 服务器要验证客户端证书
				KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
				ReadKeystore readKeystore = new ReadKeystore();
				KeyStore keyStore = readKeystore.keyStoreFromKeystore();
				keyManagerFactory.init(keyStore, KEY_STORE_PASS.toCharArray());
				KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

				// 客户端要验证服务端证书
				TrustManager[] trustManagers = { new MyX509TrustManager() };

				// 初始化SSLContext实例
				sslContext = SSLContext.getInstance("TLSv1.2");
				sslContext.init(keyManagers, trustManagers, new SecureRandom());

				// IP连接HTTPS，绕过证书校验
				HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnrecoverableKeyException e) {
				e.printStackTrace();
			} catch (KeyStoreException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
		}

		long time2 = System.currentTimeMillis();
		System.out.println("SSLContext 初始化时间：" + (time2 - time1));
		return sslContext;
	}
}
