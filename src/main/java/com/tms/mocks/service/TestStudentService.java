package com.tms.mocks.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tms.mocks.domain.Answer;
import com.tms.mocks.domain.AnswerPK;
import com.tms.mocks.domain.Test;
import com.tms.mocks.domain.TestStudent;
import com.tms.mocks.repository.AnswerRepository;
import com.tms.mocks.repository.QuestionRepository;
import com.tms.mocks.repository.TestRepository;
import com.tms.mocks.repository.TestStudentRepository;
import com.tms.mocks.service.vo.AnswerVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestStudentService {

	private final TestStudentRepository repository;
	private final TestRepository testRepository;
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository; 

	public TestStudentService(final TestStudentRepository repository, final TestRepository testRepository, final AnswerRepository answerRepository, final QuestionRepository questionRepository) {
		this.repository = repository;
		this.testRepository = testRepository;
		this.answerRepository = answerRepository;
		this.questionRepository = questionRepository;
	}

	public List<TestStudent> getUserAndExamStatusWiseTestStudent(final Long userId, final Boolean complete) {
		return repository.findByUserIdAndExamEndSuccessfully(userId, complete);
	}
	
	public List<Test> getValidTestList(final List<TestStudent> testStudents) {
		List<Test> tests = testStudents
				.stream()
				.map(testStudent -> testRepository.findById(testStudent.getTestId()).orElse(null))
				.collect(Collectors.toList());
		
		log.info("Raw TestList >>> {}", tests);
		return tests
				.stream()
				.filter(test -> {
					if(test != null && test.getValidFrom().before(new Date()) && test.getValidTo().after(new Date())) {
						return true;
					}
					return false;
				})
				.collect(Collectors.toList());
	}
	
	public List<Test> getHistoryTestList(final List<TestStudent> testStudents) {
		List<Test> tests = testStudents
				.stream()
				.map(testStudent -> testRepository.findById(testStudent.getTestId()).orElse(null))
				.collect(Collectors.toList());
		
		return tests
				.stream()
				.filter(test -> {
					if(test != null) {
						return true;
					}
					return false;
				})
				.collect(Collectors.toList());
	}

	public Optional<TestStudent> findById(final Long id) {
		return repository.findById(id);
	}

	public TestStudent save(final TestStudent testStudent) {
		return repository.save(testStudent);
	}
	
	public void saveAll(final List<TestStudent> testStudents) {
		repository.saveAll(testStudents);
	}

	public void delete(final Long id) {
		repository.deleteById(id);
	}

	public TestStudent update(final Long id, final TestStudent updateTestStudent) {
		Optional<TestStudent> testStudent = findById(id);

		if(testStudent.isPresent()) {
			TestStudent ts = testStudent.get();
			ts.setExamEndSuccessfully(updateTestStudent.getExamEndSuccessfully());
			ts.setMarksObtained(updateTestStudent.getMarksObtained());
			ts.setNegativeMarking(updateTestStudent.getNegativeMarking());
			ts.setExamStartTime(updateTestStudent.getExamStartTime());
			ts.setExamEndTime(updateTestStudent.getExamEndTime());
			return repository.save(ts);
		} else {
			return null;
		}
	}
	
	public void submitAnswers(final List<AnswerVO> answerVOs) {
		List<Answer> answers = answerVOs
					.stream()
					.map(vo -> Answer
							.builder()
							.id(new AnswerPK(vo.getTestStudentId(), vo.getQuestionId()))
							.answerGiven(vo.getAnswerGiven())
							.correct(vo.getCorrect())
							.build())
					.collect(Collectors.toList());

		Optional<TestStudent> testStudent = findById(answerVOs.get(0).getTestStudentId());
		testStudent.ifPresent(object -> {
			TestStudent
					.builder()
					.examEndSuccessfully(true)
					.marksObtained(answerVOs
							.parallelStream()
							.filter(vo -> vo.getCorrect() != null && vo.getCorrect())
							.mapToInt(vo -> questionRepository.findById(vo.getQuestionId()).orElse(null)
									.getMarks())
							.sum())
					.negativeMarking(answerVOs
							.parallelStream()
							.filter(vo -> vo.getCorrect() != null && !vo.getCorrect())
							.mapToInt(vo -> questionRepository.findById(vo.getQuestionId()).orElse(null)
									.getNegativeMark())
							.sum())
					.examStartTime(object.getExamStartTime())
					.examEndTime(new Timestamp(new Date().getTime()))
					.build();
			repository.save(object);
		});
		
		answerRepository.saveAll(answers);
	}
}
