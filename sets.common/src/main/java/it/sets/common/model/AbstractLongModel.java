package it.sets.common.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractLongModel implements IModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public AbstractLongModel() {

    }
    
    public AbstractLongModel(Long id) {
    	this.id = id;
    }
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
        AbstractLongModel other = (AbstractLongModel) obj;
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
		return new StringBuilder("\"id\":").append(String.valueOf(getId())).toString();
	}
    
	/**
	 * Convert Object to Json using StringBuilder -> ex. new StringBuilder("{\"name\":\"").append(name).append("\", \"description\":\"").append(description).append("\"}").
	 * 
	 * Use toStringAbstractModel above to convert abstractClass fields.
	 * 
	 */
    public abstract String toString();

}
