import com.lookie.toy_front_2021.model.AnswerSimple
import com.lookie.toy_front_2021.model.UserPut
import com.lookie.toy_front_2021.model.UserSend
import com.lookie.toy_front_2021.model.UserSimple
import com.lookie.toy_front_2021.network.*
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NetworkTest {
    @Test
    fun ktor_get_json() {
        runBlocking {

        }
    }



    @Test
    fun test3() {
        runBlocking {
//            val answer = getAnswer(q_num = 45, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2ltdWxlbyIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjMwNzk5Mzc1LCJleHAiOjE2MzA4MDExNzV9.LBZiOxdSn6e_Z4e1IlwSzQNKkcBAI-syT-2sMZGwzaQ")
//            println(answer)
//            val answer = postAnswer(answer = AnswerSimple(content = "muleo 답변 1", u_num = 115), q_num = 45 , token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2ltdWxlbyIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjMwNzk5Mzc1LCJleHAiOjE2MzA4MDExNzV9.LBZiOxdSn6e_Z4e1IlwSzQNKkcBAI-syT-2sMZGwzaQ")
            println(getAnswer(q_num = 45, a_num = 35, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2ltdWxlbyIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjMwNzk5Mzc1LCJleHAiOjE2MzA4MDExNzV9.LBZiOxdSn6e_Z4e1IlwSzQNKkcBAI-syT-2sMZGwzaQ"))
//            println(answer)
//            deleteQuestion(15, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2ltdWxlbyIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjMwNzk3MDc3LCJleHAiOjE2MzA3OTg4Nzd9.OP8uzMTPCAjU_Kx3NhHpGTjpo_L4BvvpCu4V7JJsYdQ")
//            println(getAnswer(q_num = 15, token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2ltdWxlbyIsInJvbGVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjMwNzk3MDc3LCJleHAiOjE2MzA3OTg4Nzd9.OP8uzMTPCAjU_Kx3NhHpGTjpo_L4BvvpCu4V7JJsYdQ"))
        }
    }
}