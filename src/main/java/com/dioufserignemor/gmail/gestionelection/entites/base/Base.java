package com.dioufserignemor.gmail.gestionelection.entites.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Base implements Serializable{
		
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false)
	protected Long id;
	
	@Column(name = "created_date", updatable = false)
	@Temporal(TIMESTAMP)
	@CreatedDate
	@JsonIgnore
    protected Date creationDate;


	@Column(name = "modified_date")
	@LastModifiedDate
	@Temporal(TIMESTAMP)
	@JsonIgnore
	protected Date lastModifiedDate;

	@CreatedBy
	@Column(name="created_by", updatable = false)
	@JsonIgnore
	protected String createdBy;

	@LastModifiedBy
	@Column(name="modified_by")
	@JsonIgnore
	protected String modifiedBy;
	

	@Column(name = "optlock", nullable = false)
	@JsonIgnore
	@Version
    private long version;

}