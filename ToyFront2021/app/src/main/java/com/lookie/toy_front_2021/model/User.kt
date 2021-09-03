package com.lookie.toy_front_2021.model

open class UserSend(
    open val name : String,
    open val phone : String,
    open val username : String,
    open val password : String,
)


data class UserReceive(
    override val name : String,
    override val phone : String,
    override val username : String,
    override val password : String,
    val u_num : Long?,
    val role : String?,
) : UserSend(name, phone, username, password)