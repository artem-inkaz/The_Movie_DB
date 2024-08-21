package ru.androidschool.intensiv.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.SearchToolbarBinding
import ru.androidschool.utils.Constants.MIN_LENGTH_WORD
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SearchBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    lateinit var binding: SearchToolbarBinding

    private var hint: String = ""
    private var isCancelVisible: Boolean = true
    private val subject = PublishSubject.create<String>()
    init {
        if (attrs != null) {
            context.obtainStyledAttributes(attrs, R.styleable.SearchBar).apply {
                hint = getString(R.styleable.SearchBar_hint).orEmpty()
                isCancelVisible = getBoolean(R.styleable.SearchBar_cancel_visible, true)
                recycle()
            }
        }
    }

    fun setText(text: String?) {
        binding.searchEditText.setText(text)
    }

    fun clear() {
        binding.searchEditText.setText("")
    }

    fun onTextChanged(): Observable<String> {
        return subject
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { it.trim() }
            .filter { it.length > MIN_LENGTH_WORD }
            .distinctUntilChanged()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = SearchToolbarBinding.inflate(LayoutInflater.from(context), this, true)
        binding.searchEditText.hint = hint
        binding.deleteTextButton.setOnClickListener {
            binding.searchEditText.text.clear()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        binding.searchEditText.afterTextChanged { text ->
            subject.onNext(text.toString())
            Timber.d("onTextChanged: $text")
            if (!text.isNullOrEmpty() && !binding.deleteTextButton.isVisible) {
                binding.deleteTextButton.visibility = View.VISIBLE
            }
            if (text.isNullOrEmpty() && binding.deleteTextButton.isVisible) {
                binding.deleteTextButton.visibility = View.GONE
            }
        }
    }

}
