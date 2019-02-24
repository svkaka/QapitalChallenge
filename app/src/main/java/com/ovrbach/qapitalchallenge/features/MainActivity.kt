package com.ovrbach.qapitalchallenge.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ovrbach.qapitalchallenge.R
import com.ovrbach.qapitalchallenge.common.entity.Goal
import com.ovrbach.qapitalchallenge.features.detail.GoalDetailFragment
import com.ovrbach.qapitalchallenge.features.list.GoalsFragment

class MainActivity : AppCompatActivity(), MainNavigation {

    override fun openGoalDetail(goal: Goal) {
        showDetailFragment(goal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showGoalsList()
    }

    private fun showDetailFragment(goal: Goal) {
        val fragment = GoalDetailFragment.getInstance(goal)
        fragment.navigation = this
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_content, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showGoalsList() {
        val fragment = GoalsFragment.getInstance()
        fragment.navigation = this
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_content, fragment)
            .commit()
    }
}
