package com.ovrbach.qapitalchallenge.features

import com.ovrbach.qapitalchallenge.data.entity.Goal

interface MainNavigation {
    fun openGoalDetail(goal: Goal)
}