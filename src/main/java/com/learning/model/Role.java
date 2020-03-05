package com.learning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "role")
public class Role extends Historized {

	private static final long serialVersionUID = -8858004000210805400L;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60)
	private RoleName name;

		
	   public Role() {
		   super();
	   }
	   
	    public Role(RoleName name) {
	    	super();
	        this.name = name;
	    }
	 
	    public Long getId() {
	        return id;
	    }
	 
	    public void setId(Long id) {
	        this.id = id;
	    }
	 
	    public RoleName getName() {
	        return name;
	    }
	 
	    public void setName(RoleName name) {
	        this.name = name;
	    }

	@Override
	public String toString() {
		return "Role [name=" + name + ", toString()=" + super.toString() + "]";
	}

}
