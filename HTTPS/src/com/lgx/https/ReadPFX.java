package com.lgx.https;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

public class ReadPFX {

	String pfxPath = "././resource/jiaohangtest.pfx";
	String pfxPassword = "111111";

	// 读取KeyStore
	public KeyStore keyStoreFromPFX() {
		KeyStore keyStore = null;
		try {
			keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(pfxPath);

			char[] nPassword = null;
			nPassword = pfxPassword.toCharArray();

			// 加载keystore
			keyStore.load(fis, nPassword);
			fis.close();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return keyStore;
	}

	// 读取别名
	@SuppressWarnings("unchecked")
	public String aliasFromPFX() {
		KeyStore keyStore = keyStoreFromPFX();
		Enumeration enumas;
		String keyAlias = null;
		try {
			enumas = keyStore.aliases();
			if (enumas.hasMoreElements()) {
				keyAlias = (String) enumas.nextElement();
				System.out.println("alias !!!! [" + keyAlias + "]");
			}
			System.out.println("is key entry !!!! "
					+ keyStore.isKeyEntry(keyAlias));
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return keyAlias;
	}

	// 读取证书
	public Certificate certificateFromPFX() {
		KeyStore keyStore = keyStoreFromPFX();
		String keyAlias = aliasFromPFX();
		Certificate cert = null;
		try {
			cert = keyStore.getCertificate(keyAlias);
			System.out.println("cert class !!!! " + cert.getClass().getName());
			System.out.println("cert !!!! " + cert);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return cert;

	}

	// 读取公钥
	public PublicKey publicKeyFromPFX() {
		Certificate cert = certificateFromPFX();
		PublicKey pubkey = cert.getPublicKey();
		System.out.println("public key !!!! " + pubkey);
		return pubkey;

	}

	// 读取私钥
	public PrivateKey privateKeyFromPFX() {
		KeyStore keyStore = keyStoreFromPFX();
		String keyAlias = aliasFromPFX();
		char[] nPassword = null;
		nPassword = pfxPassword.toCharArray();

		PrivateKey prikey = null;
		try {
			prikey = (PrivateKey) keyStore.getKey(keyAlias, nPassword);

		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return prikey;
	}
}
