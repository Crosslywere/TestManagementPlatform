package com.crosslywere.tmp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crosslywere.tmp.dto.ClassroomDTO;
import com.crosslywere.tmp.entity.Classroom;
import com.crosslywere.tmp.service.ClassroomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/test/classrooms")
@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
public class ClassroomTestController {

	private final ClassroomService classroomService;

	@PostMapping("/create")
	public ResponseEntity<String> createClassroom(@Valid @RequestBody ClassroomDTO.CreateRequest request) {
		var id = classroomService.createService(request);
		return ResponseEntity.ok(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Classroom> getClassroom(@PathVariable("id") String id) {
		var classroom = classroomService.getClassroom(id);
		if (classroom == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(classroom);
	}

	@GetMapping
	public ResponseEntity<List<ClassroomDTO.Response>> getClassrooms() {
		return ResponseEntity.ok(classroomService.getAllClassrooms());
	}

}
