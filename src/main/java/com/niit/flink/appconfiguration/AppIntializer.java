package com.niit.flink.appconfiguration;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("started rootconfig");
		
		return new Class[]{AppViewConfig.class,WebSocketConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("started servletconfig");

		return new Class[]{AppViewConfig.class,WebSocketConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		System.out.println("servletMapping rootconfig");

		return new String[]{"/"};
	}
	
	 


}
