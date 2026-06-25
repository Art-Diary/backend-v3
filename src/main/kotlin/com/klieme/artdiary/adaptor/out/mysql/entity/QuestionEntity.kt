package com.klieme.artdiary.adaptor.out.mysql.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "question",
    schema = "public"
)
class QuestionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null,

    @Column(name = "content", nullable = false)
    var content: String,
)