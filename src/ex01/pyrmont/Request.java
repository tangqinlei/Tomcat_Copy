/**
 * Project Name:Tomcat_Copy
 * File Name:Request.java
 * Package Name:ex01.pyrmont
 * Date:2017年4月2日上午9:38:51
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package ex01.pyrmont;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName:Request <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年4月2日 上午9:38:51 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Request {
	private InputStream input;
	private String uri;
	
	public Request(InputStream input){
		this.input=input;
	}
	
	public void parse(){
		StringBuffer request=new StringBuffer(2048);
		int i;
		byte[] buffer=new byte[2048];
		
		try{
			i=input.read(buffer);
		}catch(IOException e){
			e.printStackTrace();
			i=-1;
		}
		for(int j=0;j<i;j++){
			request.append((char)buffer[j]);
		}
		System.out.print(request.toString());
		uri=parseUri(request.toString());
	}
	
	private String parseUri(String requestString){
		int index1,index2;
		index1=requestString.indexOf(" ");
		if(index1!=-1){
			index2=requestString.indexOf(" ",index1+1);
			if(index2>index1){
				return requestString.substring(index1+1,index2);
			}
		}
		return null;
	}
	
	public String getUri(){
		return this.uri;
	}
}

