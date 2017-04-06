/**
 * Project Name:Tomcat_Copy
 * File Name:ServletProcessor.java
 * Package Name:ex01.pyrmont
 * Date:2017年4月6日下午6:44:00
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package ex01.pyrmont;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import javax.servlet.Servlet;

/**
 * ClassName:ServletProcessor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年4月6日 下午6:44:00 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ServletProcessor {

	public void process(Request request,Response response) {
		String uri=request.getUri();
		String servletName=uri.substring(uri.indexOf("/")+1);
		URLClassLoader loader=null;
		
		try{
			URL[] urls=new URL[1];
			URLStreamHandler streamHandler=null;
			File classPath=new File(HttpServer.WEB_ROOT);
			String repository=(new URL("file",null,classPath.getCanonicalPath()+File.separator)).toString();
			urls[0]=new URL(null,repository,streamHandler);
			loader=new URLClassLoader(urls);
		}catch(IOException e){
			System.out.println(e.toString());
		}
		
		Class myClass=null;
		try{
			myClass=loader.loadClass(servletName);
		}catch(ClassNotFoundException e){
			System.out.println(e.toString());
		}
		
		Servlet servlet=null;
		try{
			servlet=(Servlet)myClass.newInstance();
			servlet.service(request, response);
		}catch(Exception e){
			System.out.println(e.toString());
		}catch(Throwable e){
			System.out.println(e.toString());
		}
	}

}

