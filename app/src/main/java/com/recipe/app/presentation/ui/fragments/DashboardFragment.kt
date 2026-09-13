package com.recipe.app.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.recipe.app.R
import com.recipe.app.databinding.FragmentDashboardBinding
import com.recipe.app.presentation.ui.adapter.RecipeAdapter
import com.recipe.app.presentation.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        setupListeners()
        loadInitialRecipes()
    }

    private fun setupRecyclerView() {
        recipeAdapter = RecipeAdapter { recipe ->
            navigateToDetail(recipe.id)
        }
        binding.rvRecipes.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = recipeAdapter
        }
    }

    private fun setupObservers() {
        viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListeners() {
        binding.btnSearchAll.setOnClickListener {
            val searchQuery = binding.etSearch.text.toString()
            if (searchQuery.isNotBlank()) {
                viewModel.searchRecipes(searchQuery)
            } else {
                Toast.makeText(context, "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSeafood.setOnClickListener {
            viewModel.getRecipesByCategory("Seafood")
        }

        binding.btnDessert.setOnClickListener {
            viewModel.getRecipesByCategory("Dessert")
        }

        binding.btnVegetarian.setOnClickListener {
            viewModel.getRecipesByCategory("Vegetarian")
        }

        binding.btnBreakfast.setOnClickListener {
            viewModel.getRecipesByCategory("Breakfast")
        }
    }

    private fun loadInitialRecipes() {
        viewModel.getRecipesByCategory("Seafood")
    }

    private fun navigateToDetail(recipeId: String) {
        val action = DashboardFragmentDirections.actionDashboardFragmentToRecipeDetailFragment(recipeId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
