package com.crosslywere.tmp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CLASSROOMS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false)
	private String name;

	@Builder.Default
	private List<String> domains = new ArrayList<>();

	@Column(updatable = false)
	private LocalDateTime timeCreated;

	private LocalDateTime timeUpdated;

	@Builder.Default
	private boolean joinable = false;

	@Builder.Default
	private int maxSize = 50;

	@ManyToOne
	@JoinColumn(name = "TEACHER_ID")
	private Teacher teacher;

	@Builder.Default
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CLASSROOM_STUDENTS", joinColumns = {
			@JoinColumn(name = "CLASSROOM", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT))
	})
	private List<Student> students = new ArrayList<>();

	@PrePersist
	protected void onPersist() {
		timeUpdated = timeCreated = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		timeUpdated = LocalDateTime.now();
	}

}
