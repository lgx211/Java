package com.lgx.https;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {

	}

	// 默认信任所有的HTTPS站点证书
	public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {

	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
