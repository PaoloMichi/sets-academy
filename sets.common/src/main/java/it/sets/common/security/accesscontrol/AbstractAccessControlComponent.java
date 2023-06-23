package it.sets.common.security.accesscontrol;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import it.sets.common.model.ERole;

public abstract class AbstractAccessControlComponent {
	
	protected abstract IAccessControlRepository getRepository();
	
	protected boolean isAdmin(Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream().anyMatch(r -> (r.getAuthority().equals(ERole.ROLE_ADMIN.name()) || r.getAuthority().equals(ERole.ROLE_SUPERADMIN.name())));
	}
	
	protected boolean isAmministrazione(Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream().anyMatch(r -> (r.getAuthority().equals(ERole.ROLE_ADMIN.name()) || r.getAuthority().equals(ERole.ROLE_SUPERADMIN.name()) || r.getAuthority().equals(ERole.ROLE_MANAGER.name())));
	}
	
	protected boolean hasProfiling(Long pricipalUserId, String menuElementId) {
		return getRepository().findPermissionOpz(pricipalUserId, menuElementId).isPresent();
    }
	
	protected boolean hasProfiling(Long pricipalUserId, List<String> menuElementIds) {
		return !getRepository().findPermissions(pricipalUserId, menuElementIds).isEmpty();
    }
	
	protected List<String> retrievePermissions(Long pricipalUserId, List<String> menuElementIds) {
		return getRepository().findPermissions(pricipalUserId, menuElementIds);
    }

}
