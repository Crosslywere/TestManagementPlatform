package com.crosslywere.tmp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crosslywere.tmp.dto.ClassroomDTO;
import com.crosslywere.tmp.entity.Classroom;
import com.crosslywere.tmp.repository.ClassroomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomService {

	private final ClassroomRepository classroomRepository;

	public String createService(ClassroomDTO.CreateRequest request) {
		return classroomRepository.save(request.convert()).getId();
	}

	public Classroom getClassroom(String id) {
		return classroomRepository.findById(id).orElse(null);
	}

	public List<ClassroomDTO.Response> getAllClassrooms() {
		return classroomRepository.findAll().stream().map(ClassroomDTO.Response::mapper).toList();
	}

}
