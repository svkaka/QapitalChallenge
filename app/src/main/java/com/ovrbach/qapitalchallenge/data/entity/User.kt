package com.ovrbach.qapitalchallenge.data.entity

import com.ovrbach.qapitalchallenge.data.base.UserId
import java.io.Serializable

class User(
    val userId: UserId,
    val displayName: String,
    val avatarUrl: String
) : Serializable