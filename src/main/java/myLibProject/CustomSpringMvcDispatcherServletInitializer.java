package myLibProject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//cette class est l'equivalent du web.xmml

public class CustomSpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {AppConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"}; // equivalent d'envoie des requettes vers le dispatcher Servlet <servlet-mapping>...
	}

	
	 @Override
	    public void onStartup(ServletContext aServletContext) throws ServletException {
	        super.onStartup(aServletContext);
	        registerHiddenFieldFilter(aServletContext);
	    }

	    private void registerHiddenFieldFilter(ServletContext aContext) {
	        aContext.addFilter("hiddenHttpMethodFilter",
	                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
	    }
	
	
}
