/**
 * Project Name:Tomcat_Copy
 * File Name:Request.java
 * Package Name:ex01.pyrmont
 * Date:2017年4月2日上午9:38:51
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package ex01.pyrmont;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.*;

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
@SuppressWarnings("rawtypes")
public class Request implements ServletRequest{
	private InputStream input;
	private String uri;
	
	public Request(InputStream input){
		this.input=input;
	}
	
	/**
	 * Parses the Request.
	 * 解析请求
	 */
	public void parse(){
		//read a set of characters from the socket
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
	
	/**
	 * Parses the uri.
	 * 请求命令格式:GET /.../*.html HTTP/1.1
	 * parseUri()方法根据请求命令的的格式，使用命令中的空格来解析出uri。
	 * @param requestString the request string
	 * @return Uri String
	 */
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
	
	/**
	 * Gets the uri.
	 * 返回解析好的uri
	 * @return the uri string
	 */
	public String getUri(){
		return this.uri;
	}

	@Override
	public Object getAttribute(String arg0) {
		return null;
	}

	@Override
	public Enumeration getAttributeNames() {
		return null;
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public int getContentLength() {
		return 0;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return null;
	}

	@Override
	public String getLocalAddr() {
		return null;
	}

	@Override
	public String getLocalName() {
		return null;
	}

	@Override
	public int getLocalPort() {
		return 0;
	}

	@Override
	public Locale getLocale() {
		return null;
	}

	@Override
	public Enumeration getLocales() {
		return null;
	}

	@Override
	public String getParameter(String arg0) {
		return null;
	}

	@Override
	public Map getParameterMap() {
		return null;
	}

	@Override
	public Enumeration getParameterNames() {
		return null;
	}

	@Override
	public String[] getParameterValues(String arg0) {
		return null;
	}

	@Override
	public String getProtocol() {
		return null;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return null;
	}

	@Override
	public String getRealPath(String arg0) {
		return null;
	}

	@Override
	public String getRemoteAddr() {
		return null;
	}

	@Override
	public String getRemoteHost() {
		return null;
	}

	@Override
	public int getRemotePort() {
		return 0;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		return null;
	}

	@Override
	public String getScheme() {
		return null;
	}

	@Override
	public String getServerName() {
		return null;
	}

	@Override
	public int getServerPort() {
		return 0;
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public void removeAttribute(String arg0) {
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
	}

	@Override
	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
	}
}

