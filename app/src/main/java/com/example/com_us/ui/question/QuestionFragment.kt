package com.example.com_us.ui.question

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.com_us.R
import com.example.com_us.databinding.FragmentQuestionBinding
import com.example.com_us.ui.compose.QuestionListItem
import com.example.com_us.util.ThemeType

class QuestionFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentQuestionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val questionViewModel: QuestionViewModel by viewModels { QuestionViewModelFactory(requireContext()) }

    private var lastSelectedView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        questionViewModel.loadQuestionListByCate("")

        setThemeList()
        setComposeList()
        setThemeClickListener()

        return root
    }

    override fun onResume() {
        super.onResume()

        val themeAllView = binding.includeThemeAll
        questionViewModel.updateSelectedThemeId(themeAllView.textviewTheme.id)
    }

    private fun setThemeList() {
        questionViewModel.selectedThemeId.observe(viewLifecycleOwner, Observer {
            val selectedView = binding.root.findViewById<TextView>(it)
            val themeKor = selectedView.text.toString()
            questionViewModel.loadQuestionListByCate(ThemeType.fromKor(themeKor).toString())
            setThemeSelected(selectedView, themeKor)
        })
    }

    private fun setComposeList() {
        questionViewModel.questionListByCate.observe(viewLifecycleOwner) {
            binding.textviewQuestionCount.text = String.format(resources.getString(R.string.question_title_question_count), it.size)
            binding.composeviewQuestion.apply {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                setContent {
                    LazyColumn {
                        items(it.size) { idx ->
                            QuestionListItem(data = it[idx], onClick = { moveToQuestionDetail(it[idx].id) })
                        }
                    }
                }
            }
        }
    }

    private fun setThemeClickListener(){
        binding.includeThemeAll.textviewTheme.setOnClickListener(this)
        binding.includeThemeDaily.textviewTheme.setOnClickListener(this)
        binding.includeThemeFamily.textviewTheme.setOnClickListener(this)
        binding.includeThemeFriend.textviewTheme.setOnClickListener(this)
        binding.includeThemeRandom.textviewTheme.setOnClickListener(this)
        binding.includeThemeInterest.textviewTheme.setOnClickListener(this)
        binding.includeThemeSchool.textviewTheme.setOnClickListener(this)
    }

    private fun setThemeSelected(selectedView: TextView, themeKor: String) {
        binding.textviewQuestionTitle.text = String.format(resources.getString(R.string.question_title), themeKor)

        lastSelectedView?.let { view ->
            view.setBackgroundResource(R.drawable.shape_stroke_rect5_gray200)  // 기본 배경 리소스로 변경
            view.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_500))  // 기본 텍스트 색상으로 변경
        }

        selectedView?.let { view ->
            view.setBackgroundResource(R.drawable.shape_fill_rect5_gray700)
            view.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        }

        lastSelectedView = selectedView
    }

    private fun moveToQuestionDetail(questionId: Long) {
        val intent = Intent(activity, QuestionDetailActivity::class.java)
        intent.putExtra("questionId", questionId)
        startActivity(intent)
    }

    override fun onClick(view: View) {
        questionViewModel.updateSelectedThemeId(view.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}