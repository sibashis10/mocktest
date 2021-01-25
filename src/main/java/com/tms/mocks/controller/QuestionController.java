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
import com.tms.mocks.domain.Question;
import com.tms.mocks.service.ChapterService;
import com.tms.mocks.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class QuestionController {
	
	private final QuestionService service;
	private final ChapterService chapterService; 
	
	public QuestionController(final QuestionService service, final ChapterService chapterService) {
		this.service = service;
		this.chapterService = chapterService;
	}
	
	// Get chapter wise questions
	@GetMapping(value = "/chapters/{chapterId}/questions")
	public ResponseEntity<Object> getQuestions(final @PathVariable Long chapterId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getChapterWiseQuestion(chapterId));
	}
	
	// Get a question
	@GetMapping(value = "/questions/{id}")
	public ResponseEntity<Object> getQuestion(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	// Save question chapter wise
	@PostMapping(value = "/questions")
	public ResponseEntity<Object> saveQuestion(final @RequestBody Question question) {
		Optional<Chapter> chapter = chapterService.findById(question.getChapterId());
		
		if(chapter.isPresent()) {
			question.setChapterId(chapter.get().getId());
			log.debug("Question entity to be saved {}", question);
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(question));
		} else {
			return new ResponseEntity<>("Chapter is not found", HttpStatus.NOT_FOUND);
		}
	}

	// Update a question
	@PutMapping(value = "/questions/{id}")
	public ResponseEntity<Object> updateQuestion(final @PathVariable Long id, final @RequestBody Question updateQuestion) {
		Optional<Question> question = service.findById(id);

		if(question.isPresent()) {
			Question qs = question.get();
			qs.setChapterId(updateQuestion.getChapterId());
			qs.setQuestionImagePath(updateQuestion.getQuestionImagePath());
			qs.setRightAnswer(updateQuestion.getRightAnswer());
			qs.setMultipleChoice(updateQuestion.getMultipleChoice());
			qs.setMarks(updateQuestion.getMarks());
			qs.setNegativeMark(updateQuestion.getNegativeMark());
			qs.setNoOfOption(updateQuestion.getNoOfOption());
			
			return ResponseEntity.status(HttpStatus.OK).body(qs);
		} else {
			return new ResponseEntity<>("Question is not found", HttpStatus.NOT_FOUND);
		}		
	}
	
	// Delete a question
	@DeleteMapping(value = "/questions/{id}")
	public ResponseEntity<Object> deleteQuestion(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
