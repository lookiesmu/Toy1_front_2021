import com.lookie.toy_front_2021.model.UserSimple
import com.lookie.toy_front_2021.network.deleteUser
import com.lookie.toy_front_2021.network.getAnswer
import com.lookie.toy_front_2021.network.getUsers
import com.lookie.toy_front_2021.network.postLogin
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NetworkTest {

    @Test
    fun getAllUser() {
        runBlocking {
            val token = postLogin(UserSimple("AdminTest", "zxcvb2"))
            val u = getUsers(token)._embedded.userList.find { it.username == "AdminTest" }
            println("로그인은 성공")
            deleteUser(u_num = u!!.u_num!!, token)
        }
    }
}