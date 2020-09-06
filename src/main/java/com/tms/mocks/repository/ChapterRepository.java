package com.tms.mocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.mocks.domain.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
	
	List<Chapter> findByClassIdAndSubjectId(final Long classId, final Long subjectId);

}
