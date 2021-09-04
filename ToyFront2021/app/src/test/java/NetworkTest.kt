import com.lookie.toy_front_2021.model.UserSend
import com.lookie.toy_front_2021.network.postUser
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NetworkTest {
    @Test
    fun ktor_get_json() {
        runBlocking {
            val user = postUser(UserSend(name = "임혁", "01050931539", "kdb0841", "zxcvb2"))

            println(user.toString())
        }
    }
}