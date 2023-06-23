package it.sets.common.model;

public enum ERole {
	
	ROLE_TECH("Tech"),
	ROLE_ADMIN("Administrator"),
	ROLE_MANAGER("Manager"),
	ROLE_SUPERADMIN("Super-Administrator"),
	ROLE_CONSULTANT("Consultant");
    
    private String description;

    private ERole(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
