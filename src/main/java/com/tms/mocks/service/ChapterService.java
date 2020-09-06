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

	public List<Chapter> getClassSubjectWiseChapter(final Long classId, final Long subjectId) {
		return repository.findByClassIdAndSubjectId(classId, subjectId);
	}
	
	public Optional<Chapter> findById(final Long id) {
        return repository.findById(id);
    }
	
	public void save(final Chapter chapter) {
		repository.save(chapter);
    }
	
	public void delete(final Long id) {
		repository.deleteById(id);
    }
}
