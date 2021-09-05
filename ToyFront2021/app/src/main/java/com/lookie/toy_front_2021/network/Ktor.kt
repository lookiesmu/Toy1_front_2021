package com.lookie.toy_front_2021.network

import com.lookie.toy_front_2021.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.auth.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

const val url = "https://toy1-back.herokuapp.com"

val client = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = GsonSerializer() {
            setPrettyPrinting()
            disableHtmlEscaping()
            serializeNulls()
        }
    }
}

suspend fun postUser(user : UserSend) : UserReceive {
    val unit = client.post<UserReceive>("${url}/signup/user") {
        contentType(ContentType.Application.Json)
        body = user
    }
    return unit
}

suspend fun postAdmin(user : UserSend) : UserReceive {
    val unit = client.post<UserReceive>("${url}/signup/admin") {
        contentType(ContentType.Application.Json)
        body = user
    }
    return unit
}

suspend fun postLogin(userSimple : UserSimple) : String {
    val token = client.post<String>("${url}/login") {
        contentType(ContentType.Application.Json)
        body = userSimple
    }
    return token
}


suspend fun getUsers(token : String) : Users {
    val users : Users = client.get("${url}/users") {
        header("authorization", "bearer ${token}")
    }
    return users
}


suspend fun getUser(u_num : Long, token : String) : UserReceive {
    val user : UserReceive = client.get("${url}/user/${u_num}") {
        header("authorization", "bearer ${token}")
    }
    return user
}

suspend fun putUser(userPut : UserPut, u_num : Long, token : String) {
    client.put<Any>("${url}/user/${u_num}") {
        header("authorization", "bearer ${token}")
    }
}

suspend fun deleteUser(u_num : Long, token : String) {
    client.delete<Any>("${url}/user/${u_num}") {
        header("authorization", "bearer ${token}")
    }
}

suspend fun getQuestions(token : String) : List<Question> {
    return client.get<List<Question>>("${url}/question") {
        header("authorization", "bearer ${token}")
    }
}

suspend fun getQuestion(q_num : Long, token : String) : Question {
    return client.get("${url}/question/${q_num}") {
        header("authorization", "bearer ${token}")
    }
}

suspend fun deleteQuestion(q_num : Long, token : String) {
    client.delete<Any>("${url}/question/${q_num}") {
        header("authorization", "bearer ${token}")
    }
}

suspend fun postAnswer(answer: AnswerSimple, q_num : Long, token : String) : Answer {
    return client.post("${url}/question/${q_num}/answer") {
        contentType(ContentType.Application.Json)
        body = answer
        header("authorization", "bearer ${token}")
    }
}

suspend fun getAnswer(q_num : Long, token : String) : Answer {
    return client.get("${url}/${q_num}/answer") {
        header("authorization", "bearer ${token}")
    }
}

suspend fun getAnswer(q_num : Long, a_num : Long, token : String) : Answer {
    return client.get("${url}/${q_num}/answer/${a_num}") {
        header("authorization", "bearer ${token}")
    }
}

suspend fun deleteAnswer(q_num : Long, a_num : Long, token : String) {
    client.delete<Any>("${url}/${q_num}/answer/${a_num}") {
        header("authorization", "bearer ${token}")
    }
}