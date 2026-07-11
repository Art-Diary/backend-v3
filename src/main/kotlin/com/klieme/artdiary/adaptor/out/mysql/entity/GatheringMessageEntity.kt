package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "gathering_message", schema = "public")
class GatheringMessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "content", nullable = false, length = 2083)
    var content: String,

    @CreationTimestamp
    @Column(
        name = "created_at",
        nullable = false,
        updatable = false
    )
    var createdAt: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gathering_question_id", nullable = false)
    var gatheringQuestion: GatheringQuestionEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gathering_member_id", nullable = false)
    var gatheringMember: GatheringMemberEntity
) {
    fun update(content: String) {
        this.content = content
    }
}
