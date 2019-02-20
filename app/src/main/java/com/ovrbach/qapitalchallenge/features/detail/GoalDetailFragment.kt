package com.ovrbach.qapitalchallenge.features.detail

import android.content.Context
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
import com.ovrbach.qapitalchallenge.data.entity.Feed
import com.ovrbach.qapitalchallenge.data.entity.Goal
import com.ovrbach.qapitalchallenge.databinding.FragmentGoalDetailBinding
import com.ovrbach.qapitalchallenge.databinding.FragmentGoalDetailExperimentBinding
import com.ovrbach.qapitalchallenge.features.MainNavigation
import com.ovrbach.qapitalchallenge.util.bind
import com.ovrbach.qapitalchallenge.util.hide
import com.ovrbach.qapitalchallenge.util.show
import kotlinx.android.synthetic.main.fragment_goal_detail.*
import kotlinx.android.synthetic.main.fragment_goal_detail_experiment.view.*
import java.lang.IllegalStateException

const val GOAL_KEY = "goal_key"

class GoalDetailFragment : Fragment() {

    companion object {
        fun getInstance(goal: Goal) = GoalDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(GOAL_KEY, goal)
            }
        }
    }

    var navigation: MainNavigation? = null

    private lateinit var goal: Goal
    private lateinit var localViewModel: DetailViewModel
    private lateinit var binding: FragmentGoalDetailBinding
    private lateinit var adapter: GoalFeedAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        goal = (arguments?.getSerializable(GOAL_KEY) as? Goal)
            ?: throw IllegalStateException("Goal must be passed when creating fragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        localViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflater.bind(container, R.layout.fragment_goal_detail)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goal = goal
        binding.executePendingBindings()

        adapter = GoalFeedAdapter()
        view.detail_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@GoalDetailFragment.adapter
        }

        home.setOnClickListener {
            activity?.onBackPressed()
        }

        localViewModel.feedMutableData.observe(this, Observer { status ->
            when (status) {
                is Request.Success<List<Feed>> -> onSuccess(status.data)
                is Request.Error -> onError(status.error)
                is Request.Waiting -> onWaiting()
            }
        })

        localViewModel.lastWeekSum.observe(this, Observer { sum ->
            binding.sum = sum
            binding.executePendingBindings()
        })

        localViewModel.loadFeed(goal.id)
    }

    private fun onSuccess(feed: List<Feed>) {
        view?.detail_error.hide()
        view?.detail_progress.hide()

        view?.detail_recycler?.show()
        adapter.submitList(feed)
    }

    private fun onError(error: Throwable) {
        view?.detail_recycler.hide()
        view?.detail_progress.hide()

        view?.detail_error?.apply {
            text = error.localizedMessage
            show()
        }
    }

    private fun onWaiting() {
        view?.detail_error.hide()
        view?.detail_recycler?.hide()

        view?.detail_progress.show()
    }
}