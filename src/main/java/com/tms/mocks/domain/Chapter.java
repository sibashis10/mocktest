package com.tms.mocks.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CHAPTERS")
public class Chapter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "CHAPTERS_ID_GENERATOR", sequenceName = "CHAPTERS_ID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAPTERS_ID_GENERATOR")
	private Long id;

	private String name;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "subject_id", nullable = true)
	private Subject subject;

	@CreationTimestamp
	@Column(name = "created_on")
	private Timestamp createdOn;

	@UpdateTimestamp
	@Column(name = "modified_on")
	private Timestamp modifiedOn;

}
