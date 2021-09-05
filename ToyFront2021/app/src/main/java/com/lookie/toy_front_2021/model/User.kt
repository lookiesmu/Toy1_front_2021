package com.lookie.toy_front_2021.model

data class UserSend(
    val name : String,
    val phone : String,
    val username : String,
    val password : String?,
)


data class UserReceive(
    val name : String,
    val phone : String,
    val username : String,
    val password : String?,
    val u_num : Long?,
    val role : String?,
    val _links : Any?,
)

data class UserSimple(
    val username: String,
    val password: String,
)

data class Users(
    val _embedded: UserList,
    val _links: Any,
)

data class UserList(
    val userList: List<UserReceive>,
)

data class UserPut(
    val name: String,
    val phone: String,
    val password : String,
)