package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetAnimeImage {
	
	public String imgurl;
	
	private String arrin;
	private int arraylength = 0;
	private String arraystr;
	
	public void ApiPhraser() throws IOException {
		
		
		System.out.println(imgurl);
		
	}
	
	public void apiReader(String name) throws IOException {

		name = name.replaceAll(" ", "-").toLowerCase();
		
		String url = "https://api.jikan.moe/v4/anime?q=" + name;
		URL apireq = new URL(url);
		URLConnection con = apireq.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine = in.readLine();
		
		arrin = inputLine;
		
		JSONObject obj = new JSONObject(arrin);
		JSONArray a = obj.getJSONArray("data");
		
		int i = 0;
		imgurl = a.getJSONObject(i).getJSONObject("images").getJSONObject("jpg").getString("image_url");
		arraylength = a.length();
		
		arraystr = arrin;

	}
	
	public String GetImage() throws IOException {

		
		return imgurl;
		
	}
	
	public int arrayLength() {
		
		return arraylength;
		
	}
	
	public String array() {
		
		return arraystr;
		
	}

}
