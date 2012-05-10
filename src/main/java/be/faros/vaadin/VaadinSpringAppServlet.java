package be.faros.vaadin;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

public class VaadinSpringAppServlet extends AbstractApplicationServlet {
	private static final long serialVersionUID = 2647710106451471269L;

	private WebApplicationContext ctx;
    private Class<? extends Application> applicationClass;
    private String applicationBean;

    @SuppressWarnings("unchecked")
	public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        applicationBean = servletConfig.getInitParameter("applicationBean");

        if (applicationBean == null) {
            throw new ServletException("ApplicationBean not specified in servlet parameters");
        }

        ctx = WebApplicationContextUtils.getWebApplicationContext(servletConfig.getServletContext());
        applicationClass = (Class<? extends Application>) ctx.getType(applicationBean);
    }

    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return applicationClass;
    }

    protected Application getNewApplication(HttpServletRequest request) {
        return (Application) ctx.getBean(applicationBean);
    }

}