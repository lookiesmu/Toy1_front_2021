package com.lookie.toy_front_2021.network

import Address
import Company
import Geo
import User
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*


// 함수 이름은 관계 없습니다. 원하는 함수를 추가해서 사용하세요.
suspend fun main() {
    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer() {
                setPrettyPrinting()
                disableHtmlEscaping()
                serializeNulls()
                // 여기에 GSON 빌더의 함수들을 사용해서 커스텀 할 수있습니다.
            }
        }
    }
    val response: HttpResponse = client.get("https:")
    println(response.status)
    client.close() // 종료시에 불러 줍니다.
}

suspend fun sendTest() {
    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer() {
                setPrettyPrinting()
                disableHtmlEscaping()
                serializeNulls()

            }
        }
    }
    // 예를 들어 User 라는 클래스를 post 한다고 합시다.
    client.post<Unit>("서버주소") {
        contentType(ContentType.Application.Json)
        body = User(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            address = Address(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998-3874",
                geo = Geo(
                    lat = -37.3159,
                    lng = 81.1496,
                ),
            ),
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            company = Company(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets",
            ),
        )
    }

}

suspend fun receiveTest(): List<User> {
    val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = GsonSerializer() {
                setPrettyPrinting()
                disableHtmlEscaping()
                serializeNulls()
            }
        }
    }
    val user: List<User> = client.get("https://jsonplaceholder.typicode.com/users")
    return user;
}