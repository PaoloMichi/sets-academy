package it.sets.common.security;

import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public abstract class AbstractSecurityService implements ISecurityService {
	
	protected abstract Logger getLogger();
	
	protected abstract UserDetailsService getUserDetailsService();
	
	protected abstract AuthenticationManager getAuthenticationManager();
	
	@Override
    public Object findLoggedInUser() {
		getLogger().debug("Executing abstrct findLoggedInUser");
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails)
        	return (userDetails);
        return null;
    }
	
}
