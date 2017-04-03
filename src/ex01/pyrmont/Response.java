/**
 * Project Name:Tomcat_Copy
 * File Name:Response.java
 * Package Name:ex01.pyrmont
 * Date:2017年4月2日上午9:42:33
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package ex01.pyrmont;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ClassName:Response <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年4月2日 上午9:42:33 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class Response {
	private static final int BUFFER_SIZE=1024;
	
	Request request;
	OutputStream output;
	
	public Response(OutputStream output){
		this.output=output;
	}
	
	public void setRequest(Request req){
		this.request=req;
	}
	
	public void sendStaticResource()throws IOException{
		byte[] bytes=new byte[BUFFER_SIZE];
		FileInputStream fis=null;
		
		try{
			File file=new File(HttpServer.WEB_ROOT,request.getUri());
			if(file.exists()){
				fis=new FileInputStream(file);
				int ch=fis.read(bytes, 0, BUFFER_SIZE);
				while(ch!=-1){
					output.write(bytes,0,ch);
					ch=fis.read(bytes,0,BUFFER_SIZE);
				}
			}else{
				String errorMessage="HTTP/1.1 404 File Not Found\r\n"+
						"Content-Type:text/html\r\n"+
						"\r\n"+
						"<h1>File Not Found</h1>";
				output.write(errorMessage.getBytes());
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}finally{
			if(fis!=null){
				fis.close();
			}
		}
	}
}

