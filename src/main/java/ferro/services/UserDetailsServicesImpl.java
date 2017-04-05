package ferro.services;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ferro.interfaces.ApscServiceInterf;
import ferro.model.ApscUsuarios;

@Component
@Service("userDetailsServicesImpl")
public class UserDetailsServicesImpl implements UserDetailsService {

	@Autowired
	private ApscServiceInterf apscServiceImple;
	private String j_password;
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

	public UserDetails loadUserByUsername(String j_username)throws UsernameNotFoundException {
		log.info("Dentro de Details : "+j_username);
		System.err.println("Dentro de Details : "+j_username);
		ApscUsuarios user=null;
		user = apscServiceImple.getByUserName(j_username);

		if (user != null) {
			j_password = user.getPasswordIn();
			enabled = user.getEstado();
			accountNonExpired = user.getEstado();
			accountNonLocked = user.getEstado();
			credentialsNonExpired = user.getEstado();
			System.err.println("lista de autoridad es : "+authorities.isEmpty());
			
			if(authorities.isEmpty()){
				authorities.add(new SimpleGrantedAuthority(user.getApscPerfil().getDescripcion()));
			}else{
				authorities.clear();
			}
			
			
			// Now let's create Spring Security User object
			//org.springframework.security.core.userdetails.User
			User securityUser = new User(
					j_username, j_password, enabled, accountNonExpired,
					credentialsNonExpired, accountNonLocked, authorities);
			//securityUser.
			log.info("Esta habilitado: "+securityUser.isEnabled());
			return securityUser;
		} else {
			throw new UsernameNotFoundException("User Not Found!!!");
		}

	}

}
