package it.sets.common.security.accesscontrol;

import java.util.List;
import java.util.Optional;

public interface IAccessControlRepository {
	
	/**
	 * Retrieve single Optional PermissionId (String).
	 * 
	 * @param userId
	 * @return
	 */
	Optional<String> findPermissionOpz(Long userId, String menuElementId);
	
	List<String> findPermissions(Long userId, List<String> menuElementIds);

}
