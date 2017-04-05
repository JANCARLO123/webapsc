package ferro.auxi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	// Just for setting the default target URL

	protected final Log log = LogFactory.getLog(getClass());

	public LogoutSuccessHandler(String defaultTargetURL) {
		this.setDefaultTargetUrl(defaultTargetURL);
	}

	@SuppressWarnings("unused")
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
			System.err.println("En el Handler");
			log.info("La authenticacion es : "+authentication.toString());
		// do whatever you want
			
			if(authentication==null){
				System.err.println("Entra en el if");
				authentication=SecurityContextHolder.getContext().getAuthentication();
				authentication.setAuthenticated(false);
			}else{
				System.err.println("Entra en el else");
				authentication.setAuthenticated(false);
			}
			
		super.onLogoutSuccess(request, response, authentication);
	}
}
