package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class HTTPApi {
	
	public String imgurl;
	
	private String arrin;
	private int arraylength = 0;
	private String arraystr;
	public  String malurl;
	
	
	public void apiReader(String name) throws IOException {

		name = name.replaceAll(" ", "-").toLowerCase();
		
		String url = "https://api.jikan.moe/v4/anime?q=" + name;
		
		System.out.println("Request  >> " + url);
		
		URL apireq = new URL(url);
		URLConnection con = apireq.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine = in.readLine();
		
		
		arrin = inputLine;
		
		JSONObject obj = new JSONObject(arrin);
		JSONArray a = obj.getJSONArray("data");
		
		int i = 0;
		imgurl = a.getJSONObject(i).getJSONObject("images").getJSONObject("jpg").getString("image_url");
		malurl = a.getJSONObject(i).getString("url");
		
		System.out.println("Response << " + malurl);
		
		arraylength = a.length();
		
		arraystr = arrin;

	}
	
	public int arrayLength() {
		
		return arraylength;
		
	}
	
	public String array() {
		
		return arraystr;
		
	}
	
	public String malurl() {
		
		return malurl;
	}

}
