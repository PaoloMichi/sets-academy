package it.sets.common.model;

public abstract class AbstractFileModelProperties implements IFileModelProperties {
	
	private String location;
	
	public AbstractFileModelProperties() {
		
	}
	
	public AbstractFileModelProperties(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
