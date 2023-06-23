package it.sets.common.model.web;

import java.util.List;

public class TableFieldConfig {
	
	private String id;
	
	private String readableName;

	private String type;

	private String description;
	
	private List<String> values;

	public TableFieldConfig(String id, String readableName, String type, String description, List<String> values) {
		this.id = id;
		this.readableName = readableName;
		this.type = type;
		this.description = description;
		this.values = values;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReadableName() {
		return readableName;
	}

	public void setReadableName(String readableName) {
		this.readableName = readableName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
