package com.lgx.https;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class SignAndCheck {

	// 本地签名,自己证书的私钥
	public String SignatureData(String data) {
		byte[] signByte = null;

		ReadKeystore readKeystore = new ReadKeystore();
		// 获得证书
		X509Certificate x509Certificate = (X509Certificate) readKeystore.certificateFormKeystore();
		// 取得私钥
		PrivateKey privateKey = readKeystore.privateKeyFormKeystore();
		// 构建签名
		Signature signature;
		try {
			signature = Signature.getInstance(x509Certificate.getSigAlgName());
			signature.initSign(privateKey);
			signature.update(data.getBytes());
			signByte = signature.sign();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		// Base64在jdk1.8之前之后的写法不同，导入的包也不同
		return Base64.getEncoder().encodeToString(signByte);
	}

	// 服务器验签,自己证书的公钥
	public boolean CheckSignature(String data, String sign) {
		boolean flag = true;
		ReadKeystore readKeystore = new ReadKeystore();
		// 获得证书
		X509Certificate x509Certificate = (X509Certificate) readKeystore.certificateFormKeystore();
		// 获得公钥
		PublicKey publicKey = x509Certificate.getPublicKey();
		// 构建签名
		Signature signature;
		try {
			signature = Signature.getInstance(x509Certificate.getSigAlgName());
			signature.initVerify(publicKey);
			signature.update(data.getBytes());
			flag = signature.verify(Base64.getDecoder().decode(sign.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}

		return flag;
	}
}
