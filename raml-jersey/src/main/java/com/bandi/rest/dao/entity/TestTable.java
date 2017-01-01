package com.bandi.rest.dao.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Data;

@Entity
@Table(name = "TEST_TABLE")
@Data
public class TestTable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "TEST_CHAR")
	private String testChar;

	@Column(name = "TEST_INT")
	private Integer testInt;

	@Column(name = "IC_CHAR")
	private String icChar;

	@Column(name = "ANOTHER_INT")
	private Double anotherInt;

	@Column(name = "SIMPLE_VHAR")
	private String simpleChar;

	@Column(name = "CREATED_ON", nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Date createdOn = new Date(System.currentTimeMillis());

	@Column(name = "MODIFIED_ON", nullable = false, columnDefinition = "TIMESTAMP default NOW()")
	private Date modifiedOn = new Date(System.currentTimeMillis());

}
