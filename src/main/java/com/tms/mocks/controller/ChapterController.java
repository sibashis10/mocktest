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

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.info("Insert chapter >>> " + chapter);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(chapter));
	}

	@PutMapping(value = "/chapters/{id}")
	public ResponseEntity<Object> updateChapter(final @PathVariable Long id, final @RequestBody Chapter updateChapter) {
		Optional<Chapter> chapter = service.findById(id);
		
		if(chapter.isPresent()) {
			Chapter ch = chapter.get();
			ch.setName(updateChapter.getName());
			ch.setClassId(updateChapter.getClassId());
			ch.setSubjectId(updateChapter.getSubjectId());
			return ResponseEntity.status(HttpStatus.OK).body(service.save(ch));
		} else {
			return new ResponseEntity<>("Chapter is not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/chapters/{id}")
	public ResponseEntity<Object> deleteChapter(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
