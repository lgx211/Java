package com.lgx.https;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class HttpsTest {

	public static String url = "https://221.122.73.116:443/uopsSvr";

	public static void main(String[] args) throws Exception {

		SignAndCheck signAndCheck = new SignAndCheck();
		EncryAndDecry encryAndDecry = new EncryAndDecry();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		String head = "'msgTp':'uops.010.000.01','msgSndTm':'"
				+ sdf.format(new Date())
				+ "','msgId':'"
				+ sdf.format(new Date())
				+ "00000000000000000000000000000000000000000000000001',"
				+ "'issrId':'G4000311000018','reIssrId':'C1030131001288','drctn':'02','signSN':'4000068829'";

		String body = "'cntStep':'60','cntTm':'"
				+ sdf.format(new Date())
				+ "','tList':[{"
				+ "'tType':'0201','instId':'z2004944000010','accType':'01','RPFlg':'1','tSumNo':'"
				+ (new Random().nextInt(10000) + 10000)
				+ "','tTime':'347.45','tTrxSR':'99.9567','tSysSR':'99.8989'"
				+ "},{'tType':'0202','instId':'z2004944000010','accType':'00','RPFlg':'0','tSumNo':'"
				+ (new Random().nextInt(10000) + 10000)
				+ "','tTime':'321.45','tTrxSR':'99.9664','tSysSR':'99.2129'}]";

		String data = "msgTp=uops.010.000.01&msgSndTm="
				+ sdf.format(new Date())
				+ "&msgId="
				+ sdf.format(new Date())
				+ "00000000000000000000000000000000000000000000000001"
				+ "&issrId=G4000311000018&reIssrId=C1030131001288&drctn=02&signSN=4000068829";

		String encryptBody = encryAndDecry.encryptDate(body);

		String signature = signAndCheck.SignatureData(data);

		String param = "{'request':{'head':{" + head + "},'body':{"
				+ encryptBody + "}},'signature':'" + signature + "'}";

		SSLUtil sslUtil = new SSLUtil();
		String result = sslUtil.sendPost(url, param);
		System.out.println(result);
	}
}
