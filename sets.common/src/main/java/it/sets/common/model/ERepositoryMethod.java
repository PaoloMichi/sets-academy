package it.sets.common.model;

public enum ERepositoryMethod {
	
	FIND_ALL("findAll"),
	FIND_BY_CUSTOMFILTERS("findByCustomFilters"),
	FIND_BY_ID("findById"),
	FIND_BY_ID_IN("findByIdIn"),
	SAVE("save"),
	SAVE_ALL("saveAll"),
	DELETE_BY_ID("deleteById"),
	DELETE_ALL("deleteAll");
	
	private String desc;
	
	ERepositoryMethod(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
    public static String getDescByEnum(ERepositoryMethod enu){
        for (ERepositoryMethod e : ERepositoryMethod.values())
            if (enu.equals(e)) return e.getDesc();
        return null;
    }

}
