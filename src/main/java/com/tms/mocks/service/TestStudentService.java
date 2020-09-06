package com.tms.mocks.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tms.mocks.domain.Answer;
import com.tms.mocks.domain.AnswerPK;
import com.tms.mocks.domain.TestStudent;
import com.tms.mocks.repository.AnswerRepository;
import com.tms.mocks.repository.QuestionRepository;
import com.tms.mocks.repository.TestStudentRepository;
import com.tms.mocks.service.vo.AnswerVO;

@Service
public class TestStudentService {

	private final TestStudentRepository repository;
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository; 

	public TestStudentService(final TestStudentRepository repository, final AnswerRepository answerRepository, final QuestionRepository questionRepository) {
		this.repository = repository;
		this.answerRepository = answerRepository;
		this.questionRepository = questionRepository;
	}

	public List<TestStudent> getTestUserWiseTestStudent(final Long testId, final Long userId) {
		return repository.findByTestIdAndUserId(testId, userId);
	}

	public Optional<TestStudent> findById(final Long id) {
		return repository.findById(id);
	}

	public void save(final TestStudent testStudent) {
		repository.save(testStudent);
	}

	public void delete(final Long id) {
		repository.deleteById(id);
	}

	public void update(final Long id, final TestStudent updateTestStudent) {
		Optional<TestStudent> testStudent = findById(id);

		testStudent.ifPresent(object -> {
			TestStudent
					.builder()
					.examEndSuccessfully(updateTestStudent.getExamEndSuccessfully())
					.marksObtained(updateTestStudent.getMarksObtained())
					.negativeMarking(updateTestStudent.getNegativeMarking())
					.examStartTime(updateTestStudent.getExamStartTime())
					.examEndTime(updateTestStudent.getExamEndTime())
					.build();

			repository.save(object);
		});
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
