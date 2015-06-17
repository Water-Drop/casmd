package biz;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Path("/UploadBiz")
public class UploadBiz {
	final static Integer BUFFER_SIZE = 4096;  
	//@Path("/TestUpload")
	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
    public String uploadFileToWebsite(String filePath, String fileName){
	//public String uploadFileToWebsite(){
		System.out.println("I'm in Upload Biz!");
    	String str="http://scan.nq.com/open/rpc?key=test8&style=rpchtml&category=webscan&lang=cn";
        //String filePath="/Users/Water/Downloads/infectedapk/infectedapk/_com.aijiaoyou.android.sipphone_1005_1.0.5.apk";
        //String fileName="_com.aijiaoyou.android.sipphone_1005_1.0.5.apk";
        try {
            URL url=new URL(str);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("FileName", fileName);
            connection.setRequestProperty("content-type", "text/html");
            BufferedOutputStream out=new BufferedOutputStream(connection.getOutputStream());
            
            //读取文件上传到服务器
            File file=new File(filePath);
            FileInputStream fileInputStream=new FileInputStream(file);
            byte[]bytes=new byte[1024];
            int numReadByte=0;
            while((numReadByte=fileInputStream.read(bytes,0,1024))>0)
            {
                out.write(bytes, 0, numReadByte);
            }
            out.flush();
            fileInputStream.close();
            //读取URLConnection的响应
            DataInputStream in=new DataInputStream(connection.getInputStream());
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
            
            byte[] rtn_data = new byte[BUFFER_SIZE];  
      
            int count = -1;  
      
            while ((count = in.read(rtn_data, 0, BUFFER_SIZE)) != -1)  
      
                outStream.write(rtn_data, 0, count);  
      
            rtn_data = null;  
      
            String rtn_str = new String(outStream.toByteArray(), "UTF-8");  
            /*
            String path = "/Users/Water/Downloads/test_rtn.html";  
            OutputStream fos = new FileOutputStream(path);  
            DataOutputStream dos = new DataOutputStream(fos); 
            byte[] rtn_bytes=new byte[1024];
            int rtn_numReadByte=0;
            while((rtn_numReadByte=in.read(rtn_bytes,0,1024))>0)
            {
                dos.write(rtn_bytes, 0, rtn_numReadByte);
            }
            dos.flush();
            dos.close();
            */
            in.close();
            System.out.println(rtn_str);
            int first_index = rtn_str.indexOf("<div class=\"security\">");
            int last_index = rtn_str.indexOf("</li>", first_index);
            String result_str = rtn_str.substring(first_index, last_index);
            System.out.println(result_str);
            int result_first_index = result_str.indexOf("level hight");
            int result_last_index = result_str.indexOf("\"></p>",result_first_index);
            String new_result_str = result_str.substring(result_first_index, result_last_index).replace("level hight","");
            System.out.println(new_result_str);
            if (new_result_str.equals("03")){
            	System.out.println("Safe");
            	return "Safe";
            } else {
            	System.out.println("Dangerous");
            	return "Dangerous";
            }  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
}
