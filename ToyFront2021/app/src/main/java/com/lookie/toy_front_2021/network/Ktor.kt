package com.lookie.toy_front_2021.network

import com.lookie.toy_front_2021.model.UserReceive
import com.lookie.toy_front_2021.model.UserSend
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
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
    val userReceive = client.post<UserReceive>("${url}/signup/user") {
        contentType(ContentType.Application.Json)
        body = user
    }
    return userReceive
}

