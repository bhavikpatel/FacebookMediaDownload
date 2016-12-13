package fun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class httpRequest {
	
	public static String get(String url){
		
        try { 

            URL curl = new URL(url);
            HttpURLConnection connect = (HttpURLConnection)curl.openConnection();
            connect.setRequestMethod("GET");
            connect.setRequestProperty("USER-AGENT", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9");
            connect.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;5.0");
            connect.setUseCaches(false);
            connect.setAllowUserInteraction(false);
            BufferedReader r = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            
            return total.toString();
            
        } catch(Exception e){
        	
        	return e.getMessage();
        }
		
	}
	
}
