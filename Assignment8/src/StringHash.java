
import java.security.MessageDigest;
import com.google.gson.GsonBuilder;

public class StringHash {
	
	//Applies Sha256 to a string and returns the result. 
	public static String applySha256(String input){
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
	        
			//Applies sha256 to our input, 
			byte[] hash = messageDigest.digest(input.getBytes("UTF-8"));
			// This will contain hash as hexadecimal
			StringBuffer hexaHash = new StringBuffer(); 
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexaHash.append('0');
				hexaHash.append(hex);
			}
			return hexaHash.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//Short hand helper to turn Object into a JSON string
	public static String getJson(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	
	//Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"  
	public static String getDificultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
	
	
}