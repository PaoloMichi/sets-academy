package it.sets.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractStringModel implements IModel<String> {

    @Id
	@Size(max = 100)
	@Column(nullable = false, length = 100)
    private String id;

    public AbstractStringModel() {

    }
    
    public AbstractStringModel(String id) {
    	this.id = id;
    }
    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractStringModel other = (AbstractStringModel) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }
    
    /**
     * Use in toString() implementation.
     * 
     * @return
     */
	protected String toStringAbstractModel() {
		return new StringBuilder("\"id\":\"").append(getId()).append("\"").toString();
	}
    
	/**
	 * Convert Object to Json using StringBuilder -> ex. new StringBuilder("{\"name\":\"").append(name).append("\", \"description\":\"").append(description).append("\"}").
	 * 
	 * Use toStringAbstractModel above to convert abstractClass fields.
	 * 
	 */
    public abstract String toString();

}
