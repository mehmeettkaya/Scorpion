package com.mehmetkaya.scorpion.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmetkaya.scorpion.R
import com.mehmetkaya.scorpion.core.BaseActivity
import com.mehmetkaya.scorpion.core.BindingListAdapter
import com.mehmetkaya.scorpion.databinding.ActivityMainBinding
import com.mehmetkaya.scorpion.databinding.ItemMainBinding
import com.mehmetkaya.scorpion.utils.PagingScrollListener
import com.mehmetkaya.scorpion.utils.showSnackbar
import com.mehmetkaya.scorpion.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val viewModel: MainViewModel by viewModels()

    private val mainAdapter by lazy {
        BindingListAdapter<MainItemViewData, ItemMainBinding>(
            R.layout.item_main,
            MainItemViewData.DIFF_CALLBACK
        ) {
            onBind { viewData -> binding.viewData = viewData }
            onClick { item -> binding.root.showSnackbar(item.personWithId) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
        observeViewModel()
        viewModel.getPeople()
    }

    private fun initView() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener { viewModel.onRefresh() }
        mainRecyclerView.apply {
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(object : PagingScrollListener(layoutManager) {
                override val lockLoadMore: Boolean
                    get() = viewModel.lockLoadMore

                override fun loadMoreItems() {
                    viewModel.getPeople()
                }
            })
            adapter = mainAdapter
        }
    }

    private fun observeViewModel() = with(viewModel) {
        viewStateLiveData.observe(this@MainActivity) { viewData ->
            binding.viewData = viewData
            viewData.items?.let { mainAdapter.submitList(viewData.items) }
            viewData.errorMessage?.let {
                binding.root.showSnackbar(viewData.errorMessage)
                onShownErrorMessage()
            }
        }
    }
}
