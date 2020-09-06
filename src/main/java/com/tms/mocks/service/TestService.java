package com.tms.mocks.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tms.mocks.domain.Question;
import com.tms.mocks.domain.Test;
import com.tms.mocks.repository.QuestionRepository;
import com.tms.mocks.repository.TestRepository;
import com.tms.mocks.service.vo.TestVO;

@Service
public class TestService {

	private final TestRepository repository;
	private final QuestionRepository questionRepository;
	
	public TestService(final TestRepository repository, final QuestionRepository questionRepository) {
		this.repository = repository;
		this.questionRepository = questionRepository;
	}
	
	public List<Test> getClassSubjectWiseTest(final Long classId, final Long subjectId) {
		return repository.findByClassIdAndSubjectId(classId, subjectId);
	}
	
	public Optional<Test> findById(final Long id) {
		return repository.findById(id);
	}
	
	public void save(final TestVO testVO) {
		Set<Question> questions = testVO.getQuestionIds()
				.stream()
				.map(questionId -> questionRepository.findById(questionId).orElse(null))
				.collect(Collectors.toSet());
		
		Test test = Test.builder()
			.name(testVO.getName())
			.classId(testVO.getClassId())
			.subjectId(testVO.getSubjectId())
			.description(testVO.getDescription())
			.testType(testVO.getTestType())
			.validFrom(testVO.getValidFrom())
			.validTo(testVO.getValidTo())
			.noOfQuestions(testVO.getNoOfQuestions())
			.duration(testVO.getDuration())
			.totalMarks(testVO.getTotalMarks())
			.passMarks(testVO.getPassMarks())
			.noOfAttempt(testVO.getNoOfAttempt())
			.negativeMark(testVO.getNegativeMark())
			.questions(questions)
			.build();
		
		repository.save(test);
	}
	
	public void delete(final Long id) {
		repository.deleteById(id);
	}

	public void update(final Long id, final TestVO testVO) {
		Optional<Test> test = findById(id);
		
		test.ifPresent(object -> {
			Set<Question> questions = testVO.getQuestionIds()
					.stream()
					.map(questionId -> questionRepository.findById(questionId).orElse(null))
					.collect(Collectors.toSet());
			
			Test.builder()
				.name(testVO.getName())
				.classId(testVO.getClassId())
				.subjectId(testVO.getSubjectId())
				.description(testVO.getDescription())
				.testType(testVO.getTestType())
				.validFrom(testVO.getValidFrom())
				.validTo(testVO.getValidTo())
				.noOfQuestions(testVO.getNoOfQuestions())
				.duration(testVO.getDuration())
				.totalMarks(testVO.getTotalMarks())
				.passMarks(testVO.getPassMarks())
				.noOfAttempt(testVO.getNoOfAttempt())
				.negativeMark(testVO.getNegativeMark())
				.questions(questions)
				.build();
			
			repository.save(object);
		});
	}
}
