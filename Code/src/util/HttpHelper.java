package util;

import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class HttpHelper {
	static public String SendHttpRequest(String type, String url, String xml) {
		String ResponseBody = "";
		if ("GET".equalsIgnoreCase(type)) {
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("Content-type", "application/xml");
			HttpClient httpclient = new HttpClient();
			try {
				httpclient.executeMethod(get);
				ResponseBody = get.getResponseBodyAsString();
				get.releaseConnection();
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ("POST".equalsIgnoreCase(type)) {
			PostMethod post = new PostMethod(url);
			post.setRequestBody(xml);
			post.setRequestHeader("Content-type", "application/xml");
			HttpClient httpclient = new HttpClient();
			try {
				httpclient.executeMethod(post);
				ResponseBody = post.getResponseBodyAsString();
				post.releaseConnection();
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if ("PUT".equalsIgnoreCase(type)) {
			PutMethod put = new PutMethod(url);
			put.setRequestBody(xml);
			put.setRequestHeader("Content-type", "application/xml");
			HttpClient httpclient = new HttpClient();
			try {
				httpclient.executeMethod(put);
				ResponseBody = put.getResponseBodyAsString();
				put.releaseConnection();
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		}
		return ResponseBody;
	}

}
