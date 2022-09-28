// HW4.java
// last modified: September 27, 2022
// These are some of the examples that I used:
//     https://www.baeldung.com/java-aes-encryption-decryption
//     https://howtodoinjava.com/java/java-security/java-aes-encryption-example/
//     https://github.com/jaysridhar/java-stuff/blob/master/source/rsa-encryption/src/main/java/sample/sample1.java
// Note that this is not complete.  Your task is to complete the decryption part.  


import java.io.*;
import java.util.Scanner;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class HW4 {
	public static void encrypt(String in, String out) throws Exception {
		char[] password = System.console().readPassword("Enter password: ");
		byte[] salt = new byte[8];
		SecureRandom rS = new SecureRandom();
		rS.nextBytes(salt);
		PBEKeySpec pbeSpec = new PBEKeySpec(password, salt, 5, 128);
		SecretKeyFactory skFac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		SecretKeySpec secKeySpec = new SecretKeySpec(skFac.generateSecret(pbeSpec).getEncoded(), "AES");

		byte[] iv = new byte[16];
		SecureRandom r = new SecureRandom();
		r.nextBytes(iv);
		
		Cipher cipher1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		cipher1.init(Cipher.ENCRYPT_MODE, secKeySpec, ivSpec);
				
		FileInputStream fis = new FileInputStream(in);
		FileOutputStream fos = new FileOutputStream(out);
		CipherOutputStream cos = new CipherOutputStream(fos, cipher1);
		
		byte[] b = new byte[8];
		int i = fis.read(b);
		while(i != -1) {
//			fos.write(b, 0, i);
			cos.write(b, 0, i);
			i = fis.read(b);
		}
		cos.flush();
		cos.close();
		fos.flush();
		fos.close();
		fis.close();
	}
	

	public static void decrypt(String in, String out) throws Exception {
	}

    public static void main(String args[]) {
		String action = "";
		String inputFilepath = "";
		String outputFilepath = "";
		
		try {
			if(args.length != 3) {
				throw(new Exception("Missing or invalid arguments"));
			} else {
				action = args[0];
				inputFilepath = args[1];
				outputFilepath = args[2];
			}
		
			if(action.equals("encrypt")) {
				encrypt(inputFilepath, outputFilepath);
			} else if(action.equals("decrypt")) {
				decrypt(inputFilepath, outputFilepath);
			} else {
				throw(new Exception("Invalid action"));
			}
		}
		catch (Exception ex) {
			System.out.println( ex.getMessage() );
			System.exit(1);
		}
	}
}
