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
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.*;

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
public class Response implements ServletResponse{
	private static final int BUFFER_SIZE=1024;
	Request request;
	OutputStream output;
	PrintWriter writer;
	
	public Response(OutputStream output){
		this.output=output;
	}
	
	public void setRequest(Request req){
		this.request=req;
	}
	
	/**
	 * 	this method is used to serve static pages
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	@Override
	public void flushBuffer() throws IOException {
	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		/*
		 * autoflush is true,println() will flush,but print() will not. 
		 */
		writer=new PrintWriter(output,true);
		return writer;
	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {
	}

	@Override
	public void resetBuffer() {
	}

	@Override
	public void setBufferSize(int arg0) {
	}

	@Override
	public void setCharacterEncoding(String arg0) {
	}

	@Override
	public void setContentLength(int arg0) {
	}

	@Override
	public void setContentType(String arg0) {
	}

	@Override
	public void setLocale(Locale arg0) {
	}
}

