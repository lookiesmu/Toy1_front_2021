package com.lookie.toy_front_2021.model

data class Question(
    val createdDate: Long,
    val updateDate: Long,
    val q_num: Long,
    val content: String,
    val user: UserReceive,
    val answerList: List<Answer>,
)

data class Answer(
    val createdDate: Long,
    val updateDate: Long,
    val a_num: Long,
    val content: String
)