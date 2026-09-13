package com.recipe.app.presentation.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.recipe.app.databinding.FragmentRecipeDetailBinding
import com.recipe.app.presentation.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeViewModel by viewModels()
    private val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getRecipeDetail(args.recipeId)
    }

    private fun setupObservers() {
        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipeDetail ->
            with(binding) {
                tvRecipeName.text = recipeDetail.name
                tvCategory.text = "Category: ${recipeDetail.category}"
                tvArea.text = "Area: ${recipeDetail.area}"
                tvInstructions.text = recipeDetail.instructions
                tvTags.text = "Tags: ${recipeDetail.tags ?: "N/A"}"

                Glide.with(this@RecipeDetailFragment)
                    .load(recipeDetail.image)
                    .into(ivRecipeImage)

                btnYoutube.setOnClickListener {
                    recipeDetail.youtubeUrl?.let { url ->
                        openUrl(url)
                    }
                }

                btnSource.setOnClickListener {
                    recipeDetail.sourceUrl?.let { url ->
                        openUrl(url)
                    }
                }
            }
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

    private fun openUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Cannot open URL", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
