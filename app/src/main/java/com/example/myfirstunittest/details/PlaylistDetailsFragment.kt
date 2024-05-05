package com.example.myfirstunittest.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.myfirstunittest.R
import com.example.myfirstunittest.databinding.FragmentPlaylistDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PlaylistDetailsViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

    private val args: PlaylistDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        val id = args.playlistId
        setupViewModel()
        viewModel.getPlaylistDetails(id)
        observeLoader()
        observePlaylistDetail()
        return binding.root
    }

    private fun observePlaylistDetail() {
        viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistsDetail ->
            if (playlistsDetail.getOrNull() != null) {
                setupUI(playlistsDetail)
            } else {
                Snackbar.make(
                    binding.playlistsDetailsRoot, R.string.generic_error, Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun observeLoader() {
        viewModel.loader.observe(this as LifecycleOwner) { loading ->
            when (loading) {
                true -> binding.detailsLoader.visibility = View.VISIBLE
                false -> binding.detailsLoader.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupUI(playlistsDetail: Result<PlaylistDetails>) {
        binding.playlistName.text = playlistsDetail.getOrNull()!!.name
        binding.playlistDetails.text = playlistsDetail.getOrNull()!!.details
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[PlaylistDetailsViewModel::class.java]
    }

    companion object {

        @JvmStatic
        fun newInstance() = PlaylistDetailsFragment()
    }
}