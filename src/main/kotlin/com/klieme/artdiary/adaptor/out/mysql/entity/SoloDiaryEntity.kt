package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "solo_diary",
    schema = "public"
)
class SoloDiaryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id", nullable = false)
    var visit: VisitEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    var question: QuestionEntity,

    @Column(name = "content", nullable = false, length = 2083)
    var content: String,

    @Column(name = "write_date", nullable = false)
    var writeDate: LocalDateTime,

    @Column(name = "is_public", nullable = false)
    var isPublic: Boolean,
) {
    fun update(
        question: QuestionEntity,
        answerContent: String,
        writeDate: LocalDateTime,
        isPublic: Boolean,
    ) {
        this.question = question
        this.content = answerContent
        this.writeDate = writeDate
        this.isPublic = isPublic
    }
}