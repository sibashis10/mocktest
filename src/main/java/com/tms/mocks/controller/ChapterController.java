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
import com.tms.mocks.domain.Subject;
import com.tms.mocks.service.ChapterService;
import com.tms.mocks.service.SubjectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ChapterController {
	
	private final ChapterService service;
	private final SubjectService subjectService;
	
	public ChapterController(final ChapterService service, final SubjectService subjectService) {
		this.service = service;
		this.subjectService = subjectService;
	}
	
	@GetMapping(value = "/chapters")
	public ResponseEntity<Object> getChapters() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllChapters());
	}
	
	@GetMapping(value = "subjects/{subjectId}/chapters")
	public ResponseEntity<Object> getChaptersBySubject(final @PathVariable Long subjectId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getSubjectWiseChapter(subjectId));
	}
	
	@GetMapping(value = "/chapters/{id}")
	public ResponseEntity<Object> getChapter(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@PostMapping(value = "/chapters")
	public ResponseEntity<Object> saveChapter(final @RequestBody Chapter chapter) {
		log.info("Insert chapter >>> " + chapter);
		Optional<Subject> sub = subjectService.getSubject(chapter.getSubject().getId());
		if(sub.isPresent()) {
			chapter.setSubject(sub.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(chapter));
		} else {
			return new ResponseEntity<>("Class not found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/chapters/{id}")
	public ResponseEntity<Object> updateChapter(final @PathVariable Long id, final @RequestBody Chapter updateChapter) {
		Optional<Chapter> chapter = service.findById(id);
		
		if(chapter.isPresent()) {
			Chapter ch = chapter.get();
			ch.setName(updateChapter.getName());
			ch.setSubject(updateChapter.getSubject());
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
