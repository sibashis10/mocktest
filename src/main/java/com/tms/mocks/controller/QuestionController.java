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

@RestController
public class QuestionController {
	
	private final QuestionService service;
	private final ChapterService chapterService; 
	
	public QuestionController(final QuestionService service, final ChapterService chapterService) {
		this.service = service;
		this.chapterService = chapterService;
	}
	
	// Get chapter wise questions
	@GetMapping(value = "/questions/{chapterId}")
	public ResponseEntity<Object> getAllQuestions(final @PathVariable Long chapterId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getChapterWiseQuestion(chapterId));
	}
	
	// Get a question
	@GetMapping(value = "/questions/{id}")
	public ResponseEntity<Object> getQuestion(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	// Save question chapter wise
	@PostMapping(value = "/questions/{chapterId}")
	public ResponseEntity<Object> saveQuestion(final @PathVariable Long chapterId, final @RequestBody Question question) {
		Optional<Chapter> chapter = chapterService.findById(chapterId);
		
		if(chapter.isPresent()) {
			question.setChapterId(chapter.get().getId());
			service.save(question);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return new ResponseEntity<>("Chapter is not found", HttpStatus.NOT_FOUND);
		}
	}

	// Update a question
	@PutMapping(value = "/questions/{id}")
	public ResponseEntity<Object> updateQuestion(final @PathVariable Long id, final @RequestBody Question updateQuestion) {
		Optional<Question> question = service.findById(id);

		question.ifPresent(object -> {
			Question.builder()
				.chapterId(updateQuestion.getChapterId())
				.questionImagePath(updateQuestion.getQuestionImagePath())
				.rightAnswer(updateQuestion.getRightAnswer())
				.multipleChoice(updateQuestion.getMultipleChoice())
				.marks(updateQuestion.getMarks())
				.negativeMark(updateQuestion.getNegativeMark())
				.noOfOption(updateQuestion.getNoOfOption())
				.build();
			service.save(object);
		});

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Delete a question
	@DeleteMapping(value = "/questions/{id}")
	public ResponseEntity<Object> deleteQuestion(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
