package ferro.store;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ferro.interfaces.ApscServiceInterf;
import ferro.model.ApscUsuarios;

//@Component(value = "loginMB")
//@ViewScoped
@ManagedBean
@SessionScoped
@Scope("session")
@Component(value = "loginMB")
public class LoginMB {
	
	private String j_username;
	private String j_password;
	
	//private UserIN userIN;
	@Autowired
	ApscServiceInterf apscService;
	
	/*@PostConstruct
	public void init() {
		log.info("En PostConstruct");
		//this.userIN= new UserIN();
		log.info("Fin PostConstruct");
	}*/

	//@ManagedProperty(value="#{authenticationManager}")
	@Autowired
	private AuthenticationManager authenticationManager; //= null;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public String login() throws ServletException, IOException{
		
		System.err.println("En login : user -"+this.getJ_username());
		System.err.println("En login : pass -"+this.getJ_password());
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest) context.getRequest(),(ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
       // System.err.println("La autenticacion : "+SecurityContextHolder.getContext().getAuthentication().getName());

       return null;
	
	}
	
	
	public String logout(){  
		
		invalidateSession();
		return "login";
    }
	
	//@SuppressWarnings("unused")
	private void invalidateSession()
    { FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	
      
    }
	
	
	public String cancel() {
        return null;
    }
	
	// getters and setters
	public String getJ_username() {
		return j_username;
	}


	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}


	public String getJ_password() {
		return j_password;
	}


	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}

	
	
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	
	//no funcionaba autenticacion
	
	public boolean isAuthenticated(){
	       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	       return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();

	   }
	
	public ApscUsuarios getUsuarioLog(){
		//ApscUsuarios apscUsuarios= null;
			//apscUsuarios=
			return apscService.getByUserName(j_username);
		//return apscUsuarios;
	}
	

}
