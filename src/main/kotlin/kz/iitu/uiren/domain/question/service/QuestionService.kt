package kz.iitu.uiren.domain.question.service

import kz.iitu.uiren.domain.question.model.Question
import kz.iitu.uiren.domain.question.repo.QuestionRepo
import kz.iitu.uiren.ui.dto.question.request.AddQuestionRequest
import kz.iitu.uiren.ui.dto.question.request.UpdateQuestionRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class QuestionService : IQuestionService {

    @Autowired
    private lateinit var questionRepo: QuestionRepo

    override fun addQuestion(addQuestionRequest: AddQuestionRequest): ResponseEntity<Any> {
        questionRepo.save(
            Question(
                content = addQuestionRequest.content,
                imageUrl = addQuestionRequest.imageUrl,
                audioUrl = addQuestionRequest.audioUrl,
                quizId = addQuestionRequest.quizId
            )
        )
        return ResponseEntity.ok("Saved")
    }

    override fun updateQuestion(updateQuestionRequest: UpdateQuestionRequest): ResponseEntity<Any> {
        val questionOptional = questionRepo.findById(updateQuestionRequest.id)
        if (questionOptional.isPresent) {
            val question = questionOptional.get()
            question.content = updateQuestionRequest.content
            question.imageUrl = updateQuestionRequest.imageUrl
            question.audioUrl = updateQuestionRequest.audioUrl
            questionRepo.save(question)
            return ResponseEntity.ok("Updated")
        }
        return ResponseEntity.badRequest().body("Question not found")
    }

    override fun getQuestions(quizId: Long): ResponseEntity<Any> = ResponseEntity.ok(
        questionRepo.findAllByQuizId(quizId)
    )

    override fun deleteQuestion(questionId: Long): ResponseEntity<Any> {
        val questionOptional = questionRepo.findById(questionId)
        if (questionOptional.isPresent) {
            questionRepo.delete(questionOptional.get())
            return ResponseEntity.ok("Deleted")
        }
        return ResponseEntity.badRequest().body("Question not found")
    }

}