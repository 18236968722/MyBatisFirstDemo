package cn.com.mybatis.test;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.mybatis.until.RealNameMsDesPlus;
import cn.com.mybatis.until.Rsa;
import net.sf.json.JSONObject;

public class APIcustInfoPicVerifyNoGrid {
	
	
	public static  void custInfoPicVerifyNoGrid() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("busiCode", "custInfoPicVerifyNoGrid");
		String requestSource="371333";
		params.put("requestSource", requestSource);
		String transactionID= (new SimpleDateFormat("yyyyMMddHHmmssSSSSSS")).format(new Date());
		String sn= (new SimpleDateFormat("yyyyMMddHHmmssSSS000SSS")).format(new Date());
		params.put("transactionID", transactionID);
		params.put("sn", sn);
		Map<String, String> paramsJson = new HashMap<String, String>();
		paramsJson.put("custCertNo", "411522199211280019");
		paramsJson.put("custName", "张国东");
		paramsJson.put("isCheckValiddate", "0");
		paramsJson.put("needStaffCheck", "0");
		paramsJson.put("isInterfaceCombination", "0");
		String userId="zhouyuan";
		String userPwd="123";
		paramsJson.put("userId", userId);
		paramsJson.put("userPwd", userPwd);
		String paramsJsonString = JSONObject.fromObject(paramsJson).toString();
		System.out.println(paramsJsonString);
		RealNameMsDesPlus desPlus = new RealNameMsDesPlus("ARSZI");
		params.put("paramsJson", desPlus.encrypt(paramsJsonString));
		
		Map<String, Object> images = new HashMap<String, Object>();
		images.put("picNameZ", "");
		images.put("picNameF", "");
		String imagesString = JSONObject.fromObject(images).toString();
		params.put("images", imagesString);
		String signature=requestSource+transactionID+userId+userPwd+sn;
		String privateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC6TO67g5dGTdf0gIqcki9CdSxUIKuQfGPFNc+TVDI81K2ixKXlF8Hr0i3P1I/CdrvqypWj++lU5Xm95JBYAAzVy6Z1bGqV34417LonbIwpYuTVpdhOaA2aLP6ouSSCud5HXhVU8s2AOYIBfqyMLjpy91wgDUSaQ3nJNFmhVocfVVXyEJ2f0B3dRP5XsdWDjEmSa082fvCH3mHi89yaRRXEkCkWMEyVO49JOdgBG5N6YCqu6KQWNSFb+/Ltgu71pTTHVuUXjYLH2pHU6JT+LCqFxeA6iM1gPTQzMI5vt8cBpkfR3C+MCmoLzKOgRsZk7Mrz355tzIORUitBq8wBLYEXAgMBAAECggEAcif+CUzC/0Nn1tyFExfIebKO7SXvuuNdx1QH08E932R62UgiV1TNcREjHTyksoZK874bH5aXDXxnsuJBSIsPUlotr7o1PYz0+ToLf2jWIe4FRp0PBExiOVzk1sJMCwaVm6mc7KazIaUbcosKdANHsXa9Lq6yoMxD7fR0D5aTybBWcMtIipAQv6Zsma52zZ4uda6Uvco2SGfbqcu6Kx5dR1hjrn52l54jsSinXiQeqrwjNX5Pw/SOFPAD312xrYJ33TcElG042c7CZst/PJe0OpQLUbUdVRlYAFbVTjR7v3zio349/QQtHuVBCPcYcdfxCnYQT5rw341UEL0dmiGq4QKBgQDpmPaZ3qq174CvbfPmX13hvhDkawTmMzZo/cC19h3mzRTHFG8jdZTAabtxRr9cKOBvc2INTSEnQ3nL63zKRduK0YCBEEs36IByT1WH5B/whqyW01u2/Agt2QZRttdaj7Wf/XB3KQICuZKdrlfTdptKW0de4WY+tRmdRblAIewQ+QKBgQDMKsiwX3EXm50Jq0mT7g2C0kSzq133FmlYeAQKj6XzWeyOCdmBahROcAjByyeeYYhqC5CNG1YvgdhmBUPiFm8lPROqtAFMpp5e4KiShrG2LRkFBz69NNM9pj/gJzafzQG2u0+mG8yu9F/JwKr/RD+b4/aqyaqQ1QqlRv8+Kve2jwKBgBYLqpJA8mlzLfjgbpk6PNj2IA/+jZrQEH1+HTbPAGlsx142bnqxORWjpN3+6EZhUTUTdZh9w/g5pP/6vG82go6qUO9dQ0wGdEDVfePyQpVnkFg3oDs3s+nnxnHlyJMyZtiviXKlexwYuV6KOp2pXuR4ktbAfO7EFqPCFcaYrPe5AoGAdkmrcJQ7X6Qm/b0hqfVYGp1cdIQqssdnTXxrSvsi+LT22uxV+ibNkQxT5oOfdqGneVkt7LMT4f5ms+UFCW5aAsc6J8KWpiQ6yVl8ETi8qYHDZyhlbxW19ZfmliXh/f+2qHopkWG12v92p9tatv6Bo/4VoampirQDsPJLW2dlkX0CgYBpwHuB1A9PiUlCYQRwP38ka1qlfu+fAbGTDZcXGBocoamxXSBOe333G4fwxqciUzAw3DY3zBl4tvKxMo8C4se6T2H+j8/na36Mg+QQu6wMc0ATmEYa0Ihv34hjdTxi6WhMC6D3tV6QRCmWDRSF1gxXHE4hN1XauKETgVTayH6k0g==";
		String signatureRsa = new Rsa.Encoder(privateKey).encode(signature);
		params.put("images", imagesString);
		params.put("signature", signatureRsa);
		
		String paramsJosn = JSONObject.fromObject(params).toString();
		System.out.println(JSONObject.fromObject(params));
		System.out.println(paramsJosn);
		
		String url="https://211.138.24.191:20138/smz-resapi/restservice/custInfoPicVerifyNoGrid";
		//byte[] requsetPost= HttpsUtil.doPost(url, paramsJosn);
		//System.out.println(requsetPost);
	
	}
	
	
	public static void main (String[] args) throws Exception {
		
		
		custInfoPicVerifyNoGrid();
		
//        String uri = "https://images2018.cnblogs.com/blog/644402/201804/644402-20180427150542056-582283875.jpg";
//        byte[] bytes = HttpsUtil.doGet(uri);
//        FileOutputStream fos = new FileOutputStream("D:/bing.picture-of-day.jpg");
//        fos.write(bytes);
//        fos.close();
//        System.out.println("done!");
	}

}
