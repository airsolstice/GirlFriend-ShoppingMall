package com.solstice.interceptor;
import java.io.File;  
import java.util.Locale;  
  
import org.springframework.web.servlet.view.InternalResourceView;  
/** 
 *  
 */  
public class HtmlResourceView extends InternalResourceView {  
	
	
     @Override  
     public boolean checkResource(Locale locale) {  
      File file = new File(this.getServletContext().getRealPath("/") + getUrl());  
      System.out.println(file.getPath());
      // 判断该页面是否存在  
      return file.exists();
     }  
}  