package com.lookie.toy_front_2021.model

data class Question(
    val createdDate: String,
    val updateDate: String,
    val q_num: Long,
    val content: String,
    val user: UserReceive,
    val answerList: MutableList<Answer>,
)

data class Answer(
    val createdDate: String,
    val updateDate: String,
    val a_num: Long,
    val content: String
)

data class AnswerSimple(
    val content: String,
    val u_num: Long,
)