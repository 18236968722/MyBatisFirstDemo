package cn.com.mybatis.until;

import org.bouncycastle.util.encoders.Base64;


import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by LiChaoyong on 2014/8/20.
 */
public class Rsa {
    public static class Keys {
        private String privateKey;
        private String publicKey;

        public Keys(String privateKey, String publicKey) {
            this.privateKey = privateKey;
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }
    }

    public static class Generator {
        public static Keys generate() {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            try {
                KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
                generator.initialize(2048, new SecureRandom());
                KeyPair pair = generator.generateKeyPair();
                PublicKey publicKey = pair.getPublic();
                PrivateKey privateKey = pair.getPrivate();
                return new Keys(new String(Base64.encode(privateKey.getEncoded())), new String(Base64.encode(publicKey.getEncoded())));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class Encoder {
        private PrivateKey mPrivateKey;
        private Cipher cipher;

        public Encoder(String privateKey) {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            PKCS8EncodedKeySpec privatePKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey.getBytes()));
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
                mPrivateKey = keyFactory.generatePrivate(privatePKCS8);
                cipher = Cipher.getInstance("RSA");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String encode(String source) {
            try {
                cipher.init(Cipher.ENCRYPT_MODE, mPrivateKey);
                byte[] cipherText = cipher.doFinal(source.getBytes("utf-8"));
                return new String(Base64.encode(cipherText));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class Decoder {
        private PublicKey mPublicKey;
        private Cipher cipher;

        public Decoder(String publicKey) {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            X509EncodedKeySpec publicX509 = new X509EncodedKeySpec(Base64.decode(publicKey.getBytes()));
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
                mPublicKey = keyFactory.generatePublic(publicX509);
                cipher = Cipher.getInstance("RSA");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public String decode(String source) {
            try {
                cipher.init(Cipher.DECRYPT_MODE, mPublicKey);
                byte[] output = cipher.doFinal(Base64.decode(source.getBytes()));
                return new String(output, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }
    public static void main(String[] args) {
		String signature="37133320180725091003000710zhouyuan12320180725091003719000719";    	
		String privateKey="MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC6TO67g5dGTdf0gIqcki9CdSxUIKuQfGPFNc+TVDI81K2ixKXlF8Hr0i3P1I/CdrvqypWj++lU5Xm95JBYAAzVy6Z1bGqV34417LonbIwpYuTVpdhOaA2aLP6ouSSCud5HXhVU8s2AOYIBfqyMLjpy91wgDUSaQ3nJNFmhVocfVVXyEJ2f0B3dRP5XsdWDjEmSa082fvCH3mHi89yaRRXEkCkWMEyVO49JOdgBG5N6YCqu6KQWNSFb+/Ltgu71pTTHVuUXjYLH2pHU6JT+LCqFxeA6iM1gPTQzMI5vt8cBpkfR3C+MCmoLzKOgRsZk7Mrz355tzIORUitBq8wBLYEXAgMBAAECggEAcif+CUzC/0Nn1tyFExfIebKO7SXvuuNdx1QH08E932R62UgiV1TNcREjHTyksoZK874bH5aXDXxnsuJBSIsPUlotr7o1PYz0+ToLf2jWIe4FRp0PBExiOVzk1sJMCwaVm6mc7KazIaUbcosKdANHsXa9Lq6yoMxD7fR0D5aTybBWcMtIipAQv6Zsma52zZ4uda6Uvco2SGfbqcu6Kx5dR1hjrn52l54jsSinXiQeqrwjNX5Pw/SOFPAD312xrYJ33TcElG042c7CZst/PJe0OpQLUbUdVRlYAFbVTjR7v3zio349/QQtHuVBCPcYcdfxCnYQT5rw341UEL0dmiGq4QKBgQDpmPaZ3qq174CvbfPmX13hvhDkawTmMzZo/cC19h3mzRTHFG8jdZTAabtxRr9cKOBvc2INTSEnQ3nL63zKRduK0YCBEEs36IByT1WH5B/whqyW01u2/Agt2QZRttdaj7Wf/XB3KQICuZKdrlfTdptKW0de4WY+tRmdRblAIewQ+QKBgQDMKsiwX3EXm50Jq0mT7g2C0kSzq133FmlYeAQKj6XzWeyOCdmBahROcAjByyeeYYhqC5CNG1YvgdhmBUPiFm8lPROqtAFMpp5e4KiShrG2LRkFBz69NNM9pj/gJzafzQG2u0+mG8yu9F/JwKr/RD+b4/aqyaqQ1QqlRv8+Kve2jwKBgBYLqpJA8mlzLfjgbpk6PNj2IA/+jZrQEH1+HTbPAGlsx142bnqxORWjpN3+6EZhUTUTdZh9w/g5pP/6vG82go6qUO9dQ0wGdEDVfePyQpVnkFg3oDs3s+nnxnHlyJMyZtiviXKlexwYuV6KOp2pXuR4ktbAfO7EFqPCFcaYrPe5AoGAdkmrcJQ7X6Qm/b0hqfVYGp1cdIQqssdnTXxrSvsi+LT22uxV+ibNkQxT5oOfdqGneVkt7LMT4f5ms+UFCW5aAsc6J8KWpiQ6yVl8ETi8qYHDZyhlbxW19ZfmliXh/f+2qHopkWG12v92p9tatv6Bo/4VoampirQDsPJLW2dlkX0CgYBpwHuB1A9PiUlCYQRwP38ka1qlfu+fAbGTDZcXGBocoamxXSBOe333G4fwxqciUzAw3DY3zBl4tvKxMo8C4se6T2H+j8/na36Mg+QQu6wMc0ATmEYa0Ihv34hjdTxi6WhMC6D3tV6QRCmWDRSF1gxXHE4hN1XauKETgVTayH6k0g==";
		String signatureRsa = new Rsa.Encoder(privateKey).encode(signature);
		System.out.println(signatureRsa);	
		String pub="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAukzuu4OXRk3X9ICKnJIvQnUsVCCrkHxjxTXPk1QyPNStosSl5RfB69Itz9SPwna76sqVo/vpVOV5veSQWAAM1cumdWxqld+ONey6J2yMKWLk1aXYTmgNmiz+qLkkgrneR14VVPLNgDmCAX6sjC46cvdcIA1EmkN5yTRZoVaHH1VV8hCdn9Ad3UT+V7HVg4xJkmtPNn7wh95h4vPcmkUVxJApFjBMlTuPSTnYARuTemAqruikFjUhW/vy7YLu9aU0x1blF42Cx9qR1OiU/iwqhcXgOojNYD00MzCOb7fHAaZH0dwvjApqC8yjoEbGZOzK89+ebcyDkVIrQavMAS2BFwIDAQAB";
		String ssss=new Rsa.Decoder(pub).decode(signatureRsa);
        System.out.println(ssss);
    }

}
