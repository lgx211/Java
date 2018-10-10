package com.lgx.https;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class ReadKeystore {

	public String keyStorePath = "././resource/client.keystore";
	public String certificatePath = "././resource/client.crt";
	public String alias = "{3ff077c0-db58-4a2c-90d7-010189f6cefb}";
	public String keyStorePassword = "111111";
	public String aliasPassword = "111111";

	public String trustCertificatePath = "././resource/server.cer";

	// 获得KeyStore
	public KeyStore keyStoreFromKeystore() {
		FileInputStream is;
		KeyStore keyStore = null;
		try {
			is = new FileInputStream(keyStorePath);
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(is, keyStorePassword.toCharArray());
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
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

	// 取证书
	public Certificate certificateFormKeystore() {
		KeyStore keyStore = keyStoreFromKeystore();
		Certificate certificate = null;
		try {
			certificate = keyStore.getCertificate(alias);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}

		return certificate;
	}

	// 获得私钥
	public PrivateKey privateKeyFormKeystore() {
		KeyStore keyStore = keyStoreFromKeystore();

		PrivateKey prikey = null;
		try {
			prikey = (PrivateKey) keyStore.getKey(alias, aliasPassword
					.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return prikey;
	}

	// 读取公钥
	public PublicKey publicKeyFormKeystore() {
		Certificate certificate = certificateFormKeystore();

		PublicKey pubkey = null;
		try {
			pubkey = certificate.getPublicKey();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pubkey;

	}

	// 由证书路径获得Certificate
	public Certificate getCertificate(String certificatePath) {
		CertificateFactory certificateFactory;
		Certificate certificate = null;
		try {
			certificateFactory = CertificateFactory.getInstance("X.509");
			FileInputStream in = new FileInputStream(certificatePath);

			certificate = certificateFactory.generateCertificate(in);
			in.close();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return certificate;
	}

	// 由证书路径得公钥
	public PublicKey getPublicKey() {
		Certificate certificate = getCertificate(certificatePath);
		PublicKey pubkey = null;
		try {
			pubkey = certificate.getPublicKey();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pubkey;
	}

}
