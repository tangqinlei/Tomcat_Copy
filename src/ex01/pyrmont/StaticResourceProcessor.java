/**
 * Project Name:Tomcat_Copy
 * File Name:StaticResourceProcessor.java
 * Package Name:ex01.pyrmont
 * Date:2017年4月6日下午6:44:28
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved.
 *
*/

package ex01.pyrmont;

import java.io.IOException;

/**
 * ClassName:StaticResourceProcessor <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年4月6日 下午6:44:28 <br/>
 * @author   Administrator
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StaticResourceProcessor {

	public void process(Request request, Response response) {
		try{
			response.sendStaticResource();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}

