package kz.iitu.uiren.domain.answer.service

import kz.iitu.uiren.domain.answer.model.Answer
import kz.iitu.uiren.domain.answer.model.TestResult
import kz.iitu.uiren.domain.answer.repo.AnswerRepo
import kz.iitu.uiren.domain.answer.repo.TestResultRepo
import kz.iitu.uiren.domain.lesson.repo.LessonRepo
import kz.iitu.uiren.domain.question.repo.QuestionRepo
import kz.iitu.uiren.domain.quiz.model.QuizType
import kz.iitu.uiren.domain.quiz.repo.QuizRepo
import kz.iitu.uiren.domain.user.model.User
import kz.iitu.uiren.domain.user.repo.UserRepo
import kz.iitu.uiren.ui.dto.answer.request.AddAnswerRequest
import kz.iitu.uiren.ui.dto.answer.request.QuestionAnswerRequest
import kz.iitu.uiren.ui.dto.answer.request.UpdateAnswerRequest
import kz.iitu.uiren.ui.dto.quiz.response.TestResultResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AnswerService : IAnswerService {

    @Autowired
    private lateinit var answerRepo: AnswerRepo

    @Autowired
    private lateinit var quizRepo: QuizRepo

    @Autowired
    private lateinit var testResultRepo: TestResultRepo

    @Autowired
    private lateinit var questionRepo: QuestionRepo

    @Autowired
    private lateinit var userRepo: UserRepo

    @Autowired
    private lateinit var lessonRepo: LessonRepo

    override fun addAnswer(addAnswerRequest: AddAnswerRequest): ResponseEntity<Any> {
        answerRepo.save(
            Answer(
                content = addAnswerRequest.content,
                imageUrl = addAnswerRequest.imageUrl,
                audioUrl = addAnswerRequest.audioUrl,
                questionId = addAnswerRequest.questionId,
                isRight = addAnswerRequest.isRight
            )
        )
        return ResponseEntity.ok("Saved")
    }

    override fun updateAnswer(updateAnswerRequest: UpdateAnswerRequest): ResponseEntity<Any> {
        val answerOptional = answerRepo.findById(updateAnswerRequest.id)
        if (!answerOptional.isPresent) {
            return ResponseEntity.badRequest().body("Answer not found")
        }
        answerRepo.save(answerOptional.get().apply {
            imageUrl = updateAnswerRequest.imageUrl
            audioUrl = updateAnswerRequest.audioUrl
            isRight = updateAnswerRequest.isRight
            isRight = updateAnswerRequest.isRight
        })
        return ResponseEntity.ok("Updated")
    }

    override fun deleteAnswer(answerId: Long): ResponseEntity<Any> {
        val answerOptional = answerRepo.findById(answerId)
        if (!answerOptional.isPresent) {
            return ResponseEntity.badRequest().body("Answer not found")
        }
        answerRepo.delete(answerOptional.get())
        return ResponseEntity.ok("Deleted")
    }

    override fun findAllAnswers(questionId: Long): ResponseEntity<Any> = ResponseEntity.ok(
        answerRepo.findAllByQuestionId(questionId)
    )

    override fun findAllTestResults(user: User): ResponseEntity<Any> {
        val response = arrayListOf<TestResultResponse>()
        val testResults = testResultRepo.findAllByUserId(user.id!!)
        for (testResult in testResults) {
            val quiz = quizRepo.findById(testResult.quizId!!)
            if (quiz.isPresent) {
                response.add(
                    TestResultResponse(
                        total = testResult.total!!,
                        correct = testResult.correct!!,
                        passedTime = testResult.passedTime,
                        quiz = quiz.get()
                    )
                )
            }
        }
        return ResponseEntity.ok(response)
    }

    override fun passQuiz(user: User, answers: QuestionAnswerRequest): ResponseEntity<Any> {
        val total = questionRepo.findAllByQuizId(answers.quizId).size
        var correct = 0
        for (answerId in answers.answers) {
            val answerOptional = answerRepo.findById(answerId)
            if (answerOptional.isPresent) {
                if (answerOptional.get().isRight) {
                    correct++
                }
            }
        }
        testResultRepo.save(
            TestResult(
                userId = user.id,
                total = total,
                correct = correct,
                quizId = answers.quizId
            )
        )
        val quizOptional = quizRepo.findById(answers.quizId)
        if (quizOptional.isPresent) {
            val quiz = quizOptional.get()
            if (quiz.type == QuizType.COURSE.name) {
                user.quizId = null
                user.courseId = null
                user.lessonId = null
            } else {
                val lesson = lessonRepo.findById(quiz.forId!!).get()
                val lessons = lessonRepo.findAllByCourseId(lesson.courseId!!)
                for (i in lessons.indices) {
                    if (lessons[i].id == lesson.id) {
                        if (i < lessons.size - 1) {
                            user.quizId = null
                            user.lessonId = lessons[i + 1].id
                        }
                    }
                }
            }
            userRepo.save(user)
        } else {
            user.quizId = null
            user.courseId = null
            user.lessonId = null
            userRepo.save(user)
        }
        return ResponseEntity.ok("Passed")
    }

}