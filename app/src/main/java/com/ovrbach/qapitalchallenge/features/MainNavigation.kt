package com.ovrbach.qapitalchallenge.features

import com.ovrbach.qapitalchallenge.common.entity.Goal


interface MainNavigation {
    fun openGoalDetail(goal: Goal)
}