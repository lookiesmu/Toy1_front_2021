import com.lookie.toy_front_2021.network.receiveTest
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NetworkTest {
    @Test
    fun ktor_get_json() {
        runBlocking {
            val user = receiveTest()
            println("성공적으로 유저를 받아왔습니다.")
            println(user.toString())
        }
    }


}