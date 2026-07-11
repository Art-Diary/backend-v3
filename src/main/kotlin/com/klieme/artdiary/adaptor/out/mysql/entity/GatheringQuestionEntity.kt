package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*

@Entity
@Table(name = "gathering_question", schema = "public")
class GatheringQuestionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "content", nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id", nullable = false)
    var visit: VisitEntity
) {
    fun update(content: String) {
        this.content = content
    }
}
