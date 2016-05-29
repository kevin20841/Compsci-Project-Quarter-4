package JavaFXGUI;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.*;


public class SettingConfig {
	public static String generateHash(String password, String salt){
		String p = password + salt;
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(p.getBytes());
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        p = sb.toString();
		return p;
		
	}
	
	public static void writeSettingsFile(String path, String password){
		SecureRandom random = new SecureRandom();
		String salt = new BigInteger(130, random).toString(32);
		String passwordHash = generateHash(password, salt);
		
		try {
			PrintWriter writer = new PrintWriter(new FileOutputStream(path, false));
			writer.println(passwordHash);
			writer.println(salt);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
