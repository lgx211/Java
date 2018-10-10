package com.lgx.https;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class EncryAndDecry {

	// 本地加密，服务器的公钥
	// 因为没有服务器的私钥，假装这边是服务器。
	public String encryptDate(String data) throws Exception {
		byte[] encryptByte = null;
		ReadKeystore readKeystore = new ReadKeystore();
		// 取得公钥
		PublicKey publicKey = readKeystore.publicKeyFormKeystore();
		// 对数据加密
		Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		encryptByte = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(encryptByte);

	}

	// 服务器解密，服务器的私钥
	// 因为没有服务器的私钥，假装这边是服务器。
	public String decryptDate(String data) throws Exception {
		byte[] decryptByte = null;

		// 取得私钥
		ReadKeystore readKeystore = new ReadKeystore();
		PrivateKey privateKey = readKeystore.privateKeyFormKeystore();

		// 对数据解密
		Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		decryptByte = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(decryptByte);

	}

}
