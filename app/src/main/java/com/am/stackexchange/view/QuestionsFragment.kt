package com.am.stackexchange.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.am.stackexchange.R
import com.am.stackexchange.model.CustomResponse
import com.am.stackexchange.model.data.Item
import com.am.stackexchange.viewmodel.StackExchangeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    private lateinit var questionsRecyclerView: RecyclerView
    private var adapter: QuestionsAdapter? = null

    private val viewModel: StackExchangeViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.questions_fragment, container, false)
        questionsRecyclerView = view.findViewById(R.id.questions_recycler_view)
        questionsRecyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.questions.observe(viewLifecycleOwner){ response ->
            when (response) {
                is CustomResponse.OK -> {
                    response.data?.items?.let { updateUI(it) }
                }
                is CustomResponse.Error -> {
                    AlertDialog.Builder(requireContext(), R.style.AlertDialogCustom)
                        .setMessage(response.message)
                        .setPositiveButton("OK") { _,_ -> }
                        .create()
                        .show()
                }
            }

        }
    }

    private fun updateUI(questions: List<Item>) {
        adapter = activity?.let { QuestionsAdapter(it, questions) }
        questionsRecyclerView.adapter = adapter
    }

    private inner class QuestionHolder(view: View): RecyclerView.ViewHolder(view){
        val questionTitle: TextView = itemView.findViewById(R.id.question_title)
        val goToQuestionButton: ImageView = itemView.findViewById(R.id.go_to_question_details_button)
    }

    private inner class QuestionsAdapter(val activity: FragmentActivity, var questions: List<Item>)
        : RecyclerView.Adapter<QuestionHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
            val view = layoutInflater.inflate(R.layout.questions_item, parent, false)
            return QuestionHolder(view)
        }

        override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
            val question = questions[position]
            holder.apply {
                questionTitle.apply {
                    text = question.title

                    setOnClickListener {
                        goToQuestionDetails()
                    }
                }
                goToQuestionButton.apply {
                    setOnClickListener {
                        goToQuestionDetails()
                    }
                }
            }

        }

        private fun goToQuestionDetails() {
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, QuestionFragment())
                .commit()
        }

        override fun getItemCount() = questions.size
    }

}