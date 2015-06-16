package service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

@Path("/AnalysisService")
public class AnalysisServiceAPI {
	@Context HttpServletRequest req; 	
	@Path("/CheckByMD5")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String  CheckByMD5(String param){
		JSONObject paramjson = JSONObject.fromObject(param);
		String fileMD5 = paramjson.getString("fileMD5");
		
		return "";
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
