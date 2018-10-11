package com.lgx.https;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/*
 * 走代理服务器
 */
public class Ssl {
	public static void sslSocket2() throws Exception {
		// 初始化 SSLContext
		SSLContext context = SSLContext.getInstance("SSL");
		TrustManager[] tm = { new MyX509TrustManager() {
		} };
		context.init(null, tm, new SecureRandom());

		// 代理服务器
		Socket socket = new Socket("182.245.1.10", 8080);

		OutputStream output1 = socket.getOutputStream();
		StringBuffer connectMsg = new StringBuffer("connect success !!! ");
		output1.write(connectMsg.toString().getBytes());
		output1.flush();

		InputStream input1 = socket.getInputStream();
		byte[] buf1 = new byte[1024];
		input1.read(buf1);
		System.out.println(new String(buf1));

		SSLSocketFactory factory = context.getSocketFactory();
		SSLSocket sslSocket = (SSLSocket) factory.createSocket(socket, "mail.163.com", 443, true);
		
		// SSLSocket s = (SSLSocket) factory.createSocket("mail.163.com", 443);
		// s.startHandshake();
		
		OutputStream output = sslSocket.getOutputStream();
		InputStream input = sslSocket.getInputStream();

		output.write(
				("POST https://mail.163.com/entry/cgi/ntesdoor?df=mail163_letter&from=web&funcid=loginone&iframe=1&language=-1&passtype=1&product=mail163&net=t&style=-1&race=1139_1154_1123_bj&uid=xj-07@163.com HTTP/1.1"
						+ "\r\nAccept: application/x-ms-application, image/jpeg, application/xaml+xml, image/gif, image/pjpeg, application/x-ms-xbap, */*"
						+ "\r\nUser-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET4.0C; .NET4.0E)"
						+ "\r\nContent-Type: application/x-www-form-urlencoded" + "\r\nAccept-Encoding: gzip, deflate"
						+ "\r\nHost: mail.163.com" + "\r\nContent-Length: 106" // body的字符个数
						+ "\r\nConnection: Keep-Alive" + "\r\nCache-Control: no-cache"
						+ "\r\nReferer: http://mail.163.com/" + "\r\nAccept-Language: zh-CN" + "\r\n"
						+ "\r\nsavelogin=0&url2=http%3A%2F%2Fmail.163.com%2Ferrorpage%2Ferror163.htm&username=xj-0701&password=xxx12345yy")
								.getBytes());
		output.flush();

		byte[] buf = new byte[1024];
		int len = input.read(buf);
		System.out.println("received:" + new String(buf, 0, len));
	}

	public static void main(String[] args) throws Exception {

		sslSocket2();
	}

}