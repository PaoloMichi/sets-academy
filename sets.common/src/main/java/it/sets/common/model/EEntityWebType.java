package it.sets.common.model;

public enum EEntityWebType {
	
	USER("users"), GROUP("groups"), ROLE("roles");
	
	private String desc;
	
	EEntityWebType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
    public static EEntityWebType getEnumByDesc(String desc){
        for(EEntityWebType e : EEntityWebType.values())
            if (e.desc.equals(desc)) return e;
        return null;
    }

}
