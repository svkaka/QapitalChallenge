package com.ovrbach.qapitalchallenge.features.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ovrbach.qapitalchallenge.R
import com.ovrbach.qapitalchallenge.data.base.Request
import com.ovrbach.qapitalchallenge.data.entity.Goal
import com.ovrbach.qapitalchallenge.features.MainNavigation
import com.ovrbach.qapitalchallenge.features.MainViewModel
import com.ovrbach.qapitalchallenge.util.ItemClickListener
import com.ovrbach.qapitalchallenge.util.hide
import com.ovrbach.qapitalchallenge.util.show
import kotlinx.android.synthetic.main.fragment_goal_list.*
import kotlinx.android.synthetic.main.fragment_goal_list.view.*
import java.lang.IllegalStateException

class GoalsFragment : Fragment() {

    companion object {
        private val singleInstance by lazy {
            GoalsFragment()
        }

        fun getInstance() = singleInstance
    }

    var navigation: MainNavigation? = null

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GoalsAdapter
    private val itemClickListener: ItemClickListener<Goal> = object : ItemClickListener<Goal> {
        override fun onItemClicked(item: Goal) {
            handleClick(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.let { ViewModelProviders.of(it).get(MainViewModel::class.java) }
            ?: throw IllegalStateException("Fragment must be attached to activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_goal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = GoalsAdapter(itemClickListener)

        view.goals_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@GoalsFragment.adapter
        }

        (activity as? AppCompatActivity)?.setSupportActionBar(goals_toolbar)

        viewModel.goalsLiveData.observe(this, Observer { response ->
            when (response) {
                is Request.Success<List<Goal>> -> onSuccess(response.data)
                is Request.Error -> onError(response.error)
                is Request.Waiting -> onLoading()
            }
        })

        viewModel.fetchGoals()
    }

    private fun onSuccess(goals: List<Goal>) {
        view?.goals_progress.hide()
        view?.goals_error.hide()

        view?.goals_recycler.show()
        adapter.submitList(goals)
    }

    private fun onError(e: Throwable) {
        view?.goals_progress.hide()
        view?.goals_recycler.hide()

        view?.goals_error?.apply {
            text = e.localizedMessage
            show()
        }
    }

    private fun onLoading() {
        view?.goals_recycler.hide()
        view?.goals_error.hide()

        view?.goals_progress.show()
    }

    private fun handleClick(item: Goal) {
        navigation?.openGoalDetail(item)
    }

}
