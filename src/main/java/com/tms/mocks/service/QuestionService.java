package com.tms.mocks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tms.mocks.domain.Question;
import com.tms.mocks.repository.QuestionRepository;

@Service
public class QuestionService {
	
	private final QuestionRepository repository;
	
	public QuestionService(final QuestionRepository repository) {
		this.repository = repository;
	}
	
	public List<Question> getChapterWiseQuestion(final Long chapterId) {
		return repository.findByChapterId(chapterId);
	} 
	
	public Optional<Question> findById(final Long id) {
        return repository.findById(id);
    }
	
	public Question save(final Question question) {
		return repository.save(question);
    }
	
	public void delete(final Long id) {
		repository.deleteById(id);
    }

}
