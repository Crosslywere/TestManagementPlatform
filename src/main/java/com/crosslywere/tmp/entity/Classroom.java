package com.crosslywere.tmp.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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

	@Builder.Default
	private boolean joinable = false;

	@Builder.Default
	private int maxSize = 50;

	@PrePersist
	protected void onPersist() {
		timeCreated = LocalDateTime.now();
	}

}
