package com.ovrbach.qapitalchallenge.common.entity

import com.ovrbach.qapitalchallenge.common.base.UserId
import java.io.Serializable

class User(
    val userId: UserId,
    val displayName: String,
    val avatarUrl: String
) : Serializable