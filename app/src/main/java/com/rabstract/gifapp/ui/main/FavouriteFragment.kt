package com.rabstract.gifapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.rabstract.gifapp.R
import com.rabstract.gifapp.ui.adapter.FavoriteGifAdapter
import com.rabstract.model.FavouriteGif
import kotlinx.android.synthetic.main.fragment_favorites_gifs.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FavouriteFragment : Fragment() {
    private val viewModel: FavouriteViewModel by viewModel()
    private lateinit var commonViewModel : CommonViewModel
    private val gifAdapter: FavoriteGifAdapter by inject { parametersOf(favoriteGifIconListener) }
    private var favoriteGifIconListener: (FavouriteGif) -> Unit = {
        commonViewModel.setRefreshRequired(true)
        viewModel.handleRemoveButton(it)
        showSnackBar()
    }
    companion object {
        fun newInstance() = FavouriteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites_gifs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commonViewModel = ViewModelProvider(requireActivity()).get(CommonViewModel::class.java)


        initRecyclerView()
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favouriteGifs.collect {
                    populateRecyclerView(it)
                }
            }
        }
    }

    private fun showSnackBar() {
        Snackbar.make(
            favorites_beers_main_container,
            getString(R.string.activity_favorites_snack_bar_title),
            Snackbar.LENGTH_SHORT
        ).setAction(getString(R.string.activity_favorites_snack_bar_action)) {
            viewModel.handleUndoButton()
        }.show()
    }

    private fun initRecyclerView() {
        favorites_gif_recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    private fun populateRecyclerView(favouriteGifs: List<FavouriteGif>?) {
        favouriteGifs?.let {
            if (it.isEmpty()) {
                favorites_gif_recycler_view.visibility = View.GONE
                favorites_gifs_empty_view.visibility = View.VISIBLE
            } else {
                favorites_gif_recycler_view.apply {
                    gifAdapter.setData(favouriteGifs)
                    adapter = gifAdapter
                    setHasFixedSize(true)
                }

                favorites_gif_recycler_view.visibility = View.VISIBLE
                favorites_gifs_empty_view.visibility = View.GONE
            }
        }
    }

}