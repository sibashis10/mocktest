package com.tms.mocks.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.mocks.domain.Chapter;
import com.tms.mocks.service.ChapterService;

@RestController
public class ChapterController {
	
	private final ChapterService service;
	
	public ChapterController(final ChapterService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/chapters/{classId}/{subjectId}")
	public ResponseEntity<Object> getAllChapters(final @PathVariable Long classId, final @PathVariable Long subjectId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getClassSubjectWiseChapter(classId, subjectId));
	}
	
	@GetMapping(value = "/chapters/{id}")
	public ResponseEntity<Object> getChapter(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@PostMapping(value = "/chapters")
	public ResponseEntity<Object> saveChapter(final @RequestBody Chapter chapter) {
		service.save(chapter);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "/chapters/{id}")
	public ResponseEntity<Object> updateChapter(final @PathVariable Long id, final @RequestBody Chapter updateChapter) {
		Optional<Chapter> chapter = service.findById(id);

		chapter.ifPresent(object -> {
			Chapter.builder()
				.name(updateChapter.getName())
				.classId(updateChapter.getClassId())
				.subjectId(updateChapter.getSubjectId())
				.build();
			service.save(object);
		});

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping(value = "/chapters/{id}")
	public ResponseEntity<Object> deleteChapter(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
