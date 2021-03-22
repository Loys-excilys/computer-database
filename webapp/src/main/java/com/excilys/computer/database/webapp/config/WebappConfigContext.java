package com.excilys.computer.database.webapp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
@ComponentScan({ "com.excilys.computer.database.dao", "com.excilys.computer.database.service",
		"com.excilys.computer.database.controller", "com.excilys.computer.database.view",
		"com.excilys.computer.database.config" })
public class WebappConfigContext extends AbstractAnnotationConfigDispatcherServletInitializer{

	
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//
//		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//		ctx.register(WebMvcConfig.class);
//		ctx.setServletContext(servletContext);
//
//		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//		servlet.setLoadOnStartup(1);
//		servlet.addMapping("/");
//	}

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] {SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}
