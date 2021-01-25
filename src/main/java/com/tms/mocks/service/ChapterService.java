package com.tms.mocks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tms.mocks.domain.Chapter;
import com.tms.mocks.repository.ChapterRepository;

@Service
public class ChapterService {
	
	private final ChapterRepository repository;
	
	public ChapterService(final ChapterRepository repository) {
		this.repository = repository;
	}

	public List<Chapter> getAllChapters() {
		return repository.findAll();
	}
	
	public List<Chapter> getSubjectWiseChapter(final Long subjectId) {
		return repository.findBySubjectId(subjectId);
	}
	
	public Optional<Chapter> findById(final Long id) {
        return repository.findById(id);
    }
	
	public Chapter save(final Chapter chapter) {
		return repository.save(chapter);
		
    }
	
	public void delete(final Long id) {
		repository.deleteById(id);
    }
}
