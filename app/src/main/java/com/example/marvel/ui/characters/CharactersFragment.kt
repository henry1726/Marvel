package com.example.marvel.ui.characters


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvel.databinding.FragmentCharactersBinding
import com.example.marvel.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private val binding: FragmentCharactersBinding by lazy {
        FragmentCharactersBinding.inflate(layoutInflater)
    }
    private val viewModel: CharactersViewModel by viewModels()
    private lateinit var mAdapter: PagingCharactersAdapter//CharactersAdapter
    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel.getAllCharacters(0)
        initUi()
        initObservers()
    }

    private fun initUi() {
        with(binding){
            mAdapter = PagingCharactersAdapter(requireContext()){ model, action ->
                when(action){
                    ActionListener.CLICK_CARD -> {
                        model?.let {
                            findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(it))
                        }

                    }
                }

            }
            val mLayoutManager = GridLayoutManager(requireContext(), 2)
            characterRecyclerView.apply {
                adapter = mAdapter.withLoadStateHeaderAndFooter(
                    header = PagingCharactersLoadStateAdapter{mAdapter.retry()},
                    footer = PagingCharactersLoadStateAdapter{mAdapter.retry()}
                )
                layoutManager = mLayoutManager
            }
        }
    }

    private fun initObservers() {
        //initObserverGetAllCharacters()
        initObserverGetAllCharactersPaging()
    }

    private fun initObserverGetAllCharactersPaging() {
        lifecycleScope.launch {
            viewModel.listData.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    private fun initObserverGetAllCharacters() {
        viewModel.responseCharacter.observe(viewLifecycleOwner){ characters ->
            characters.data.let {
                when(characters.status){
                    Resource.Status.SUCCESS -> {
                        characters.data?.let { response ->
                            //binding.progressCircular.visibility = View.GONE
                            binding.characterRecyclerView.visibility = View.VISIBLE
                            response.data.results.let {list ->
                                adapter = CharactersAdapter(list.map { it.toCharacter() }, requireContext()){ model, action ->
                                    when(action){
                                        ActionListener2.CLICK_CARD -> {
                                            findNavController().navigate(CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailFragment(model))
                                        }
                                    }
                                }
                                binding.characterRecyclerView.adapter = adapter
                            }
                        }


                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(), characters.message, Toast.LENGTH_SHORT).show()
                    }
                    Resource.Status.LOADING -> {
                        //binding.progressCircular.visibility = View.VISIBLE
                        binding.characterRecyclerView.visibility = View.GONE
                    }
                }
            }
        }
    }

}