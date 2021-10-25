package com.rabstract.gifapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rabstract.gifapp.R
import com.rabstract.gifapp.ui.adapter.TrendingGifsAdapter
import com.rabstract.model.GifData
import kotlinx.android.synthetic.main.fragment_gif_results.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * A TrendingGifFragment fragment containing a simple view.
 */
private const val KEY_LAST_ITEM_POSITION = "KEY_LAST_ITEM_POSITION"

class TrendingGifFragment : Fragment() {

    private val viewModelTrending: TrendingGifViewModel by viewModel()
    private val commonViewModel: CommonViewModel by viewModel()
    private val trendingGifsAdapter: TrendingGifsAdapter by inject {
        parametersOf(
            favoriteBeerListener
        )
    }
    private var favoriteBeerListener: (GifData) -> Unit = {
        viewModelTrending.handleFavoriteButton(it)
    }
    private var recyclerView: RecyclerView? = null
    private var savedInstanceState: Bundle? = null

    companion object {
        fun newInstance() = TrendingGifFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gif_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUpSearchView()
        animateRecyclerViewOnlyInTheBeginning()
        commonViewModel.isRefreshRequired.observe(
            viewLifecycleOwner,
            { isRefreshRequired ->
                if (isRefreshRequired != null) {
                    trendingGifsAdapter.refresh()
                    trendingGifsAdapter.notifyDataSetChanged()
                }

            }
        )
        viewLifecycleOwner.lifecycleScope.launch {

            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModelTrending.gifs.collectLatest {

                    trendingGifsAdapter.submitData(it)
                }
            }
        }
        viewModelTrending.fetchGifsFromRemote()
    }

    private fun initRecyclerView() {
        recycler_view_gifs?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trendingGifsAdapter
            setHasFixedSize(true)
        }
    }


    private fun animateRecyclerViewOnlyInTheBeginning() {
        if (getLastItemPosition() == 0) {
            recyclerView?.layoutAnimation =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
        }
    }

    private fun getLastItemPosition(): Int {
        var lastItemPosition = 0
        savedInstanceState?.let {
            if (it.containsKey(KEY_LAST_ITEM_POSITION)) {
                lastItemPosition = it.getInt(KEY_LAST_ITEM_POSITION)
            }
        }
        return lastItemPosition
    }

    private fun onErrorReceived() {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.network_connection_error_title)
                .setCancelable(false)
                .setNegativeButton(R.string.network_connection_error_cancel) { _, _ -> activity?.finish() }
                .setPositiveButton(R.string.network_connection_error_action) { _, _ ->
                    viewModelTrending.fetchGifsFromRemote(
                        null
                    )
                }
                .show()
        }
    }
    private fun setUpSearchView() {

       tip_search.setEndIconOnClickListener {

            edt_search.text?.clear()

            viewModelTrending.fetchGifsFromRemote()
        }

        edt_search.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                v.hideKeyboard()

                viewModelTrending.fetchGifsFromRemote(edt_search.text.toString())

                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }

    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}