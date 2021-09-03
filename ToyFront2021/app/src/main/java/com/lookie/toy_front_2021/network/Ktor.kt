package com.lookie.toy_front_2021.network

import com.lookie.toy_front_2021.model.UserReceive
import com.lookie.toy_front_2021.model.UserSend
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*

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
    val userReceive = client.post<UserReceive>("https://localhost:8080/admin/") {
        contentType(ContentType.Application.Json)
        body = user // UserSend(name = "임혁", "01050931539", "kdb0841", "zxcvb2")
    }
    return userReceive
}

