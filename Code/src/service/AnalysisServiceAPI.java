package service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import dao.AnalysisDAO;

@Path("/AnalysisService")
public class AnalysisServiceAPI {
	AnalysisDAO ad = new AnalysisDAO();
	@Context HttpServletRequest req; 	
	@Path("/CheckByMD5")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String  CheckByMD5(String param){
		JSONObject paramjson = JSONObject.fromObject(param);
		JSONArray fileMD5s = paramjson.getJSONArray("fileMD5");
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<String> fileMD5List = JSONArray.toList(fileMD5s);
		List<String> fileLevelList = new ArrayList<String>();
		Map map = new HashMap<String, String>();
		Integer status = -1;
		if (fileMD5List.size() != 0){
			for (int i = 0; i < fileMD5List.size(); i++){
				String tmp_level = ad.getLevelByMD5(fileMD5List.get(i));
				if (tmp_level.equals("")){
					fileLevelList.add("Unknown");
				} else {
					fileLevelList.add(tmp_level);
				}
			}
			List<String> f_jsons = new ArrayList<String>();
			for (int i = 0; i < fileMD5List.size(); i++){
				Map<String, String> f_map = new HashMap<String, String>();
				f_map.put("fileMD5", fileMD5List.get(i).toString());
				f_map.put("fileLevel", fileLevelList.get(i).toString());
				JSONObject f_json = JSONObject.fromObject(f_map);
				f_jsons.add(f_json.toString());
			}		
			JSONArray jsonArray = JSONArray.fromObject(f_jsons);
			map.put("Results:", jsonArray);
			status = 0;
		}
		map.put("status", status.toString());
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	@Path("/CheckByUploadFile")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String  CheckByUploadFile(String param){
		JSONObject paramjson = JSONObject.fromObject(param);
		String fileBase64 = paramjson.getString("fileBase64");
		String fileMD5 = paramjson.getString("fileMD5");
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] decoderBytes = decoder.decodeBuffer(fileBase64);
			String regex = "data:.*;base64,"; 
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(fileBase64);
			fileBase64 = matcher.replaceAll("");
			//FileOutputStream write = new FileOutputStream(new File("./" + fileMD5));
			FileOutputStream write = new FileOutputStream(new File("/Users/Water/Desktop/SE/Project/casmd/UploadFile/" + fileMD5 + ".apk"));
			write.write(decoderBytes);
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
