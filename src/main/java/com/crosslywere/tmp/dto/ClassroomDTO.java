package com.crosslywere.tmp.dto;

import java.util.List;
import java.util.stream.Stream;

import com.crosslywere.tmp.entity.Classroom;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ClassroomDTO {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CreateRequest {

		@NotBlank
		private String name;

		private String domains;

		public Classroom convert() {
			return Classroom.builder()
					.name(name.trim())
					.domains(domains == null ? List.of()
							: Stream.of(domains.split(";")).map(String::trim)
									.filter(domain -> domain.startsWith("@") && domain.contains("."))
									.toList())
					.build();
		}

	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {

		private String id;

		private String name;

		private List<String> domains;

		private String creationTime;

		private boolean joinable;

		public static Response mapper(Classroom instance) {
			return Response.builder()
					.id(instance.getId())
					.name(instance.getName())
					.domains(instance.getDomains())
					.creationTime(instance.getTimeCreated().toString())
					.joinable(instance.isJoinable())
					.build();
		}

	}

}
