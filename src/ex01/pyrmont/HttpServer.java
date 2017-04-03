/**
 * Project Name:Tomcat_Copy
 * File Name:HttpServer.java
 * Package Name:ex01.pyrmont
 * Date:2017年4月2日上午9:27:10
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package ex01.pyrmont;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClassName:HttpServer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年4月2日 上午9:27:10 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class HttpServer {
	
	public static final String WEB_ROOT=System.getProperty("user.dir")+File.separator+"webroot";
	
	private static final String SHUTDOWN_COMMAND="/SHUTDOWN";
	
	private boolean shutdown=false;
	
	public static void main(String[] args) {
		HttpServer server=new HttpServer();
		server.await();
	}
	
	public void await(){
		ServerSocket serverSocket=null;
		int port=8080;
		
		try{
			serverSocket=new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("start");
		while(!shutdown){
			Socket socket=null;
			InputStream input=null;
			OutputStream output=null;
			
			try{
				socket=serverSocket.accept();
				input=socket.getInputStream();
				output=socket.getOutputStream();
				
				Request request=new Request(input);
				request.parse();
				
				Response response=new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
				
				socket.close();
				//关闭命令
				shutdown=request.getUri().equals(SHUTDOWN_COMMAND);
			}catch(Exception e){
				e.printStackTrace();
				continue;
			}
		}
	}
}

