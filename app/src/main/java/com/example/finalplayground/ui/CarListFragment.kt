package com.example.finalplayground.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.finalplayground.R
import com.example.finalplayground.data.network.Resource.Status.ERROR
import com.example.finalplayground.data.network.Resource.Status.LOADING
import com.example.finalplayground.data.network.Resource.Status.SUCCESS
import com.example.finalplayground.databinding.FragmentCarListBinding
import com.example.finalplayground.domain.model.Car
import com.example.finalplayground.ui.common.GenericRecyclerViewAdapter
import com.example.finalplayground.ui.common.ItemClickListener
import com.example.finalplayground.ui.common.showErrorBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarListFragment : Fragment(), ItemClickListener {
    private val carListViewModel: CarListViewModel by hiltNavGraphViewModels(R.id.carListFragment)
    private lateinit var binding: FragmentCarListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCarListBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@CarListFragment
            vm = this@CarListFragment.carListViewModel
            clickListener = this@CarListFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDivider()
        observeCars()
    }

    private fun setupDivider() {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) }
        binding.carList.addItemDecoration(itemDecorator)
    }

    private fun observeCars() {
        carListViewModel.remoteCars.observe(viewLifecycleOwner) {
            when (it.status) {
                LOADING -> binding.isLoading = true
                SUCCESS -> handleSuccessResponse(data = it.data)
                ERROR -> handleErrorResponse(msg = it.message)
            }
        }
    }

    private fun handleSuccessResponse(data: List<Car>? = null) {
        binding.isLoading = false
        binding.isEmpty = data.isNullOrEmpty()
        binding.carList.adapter = GenericRecyclerViewAdapter(data, R.layout.layout_car_item, this)
    }

    private fun handleErrorResponse(msg: String? = null) {
        binding.isLoading = false
        binding.isEmpty = true
        showErrorBar(msg)
    }

    override fun onItemClick(item: Any?) {
        // TODO: add on click functionality if needed.
    }
}
