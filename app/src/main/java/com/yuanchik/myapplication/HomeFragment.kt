package com.yuanchik.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuanchik.myapplication.databinding.FragmentDetailsBinding
import com.yuanchik.myapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var filmsAdapter: FilmListRecyclerAdapter

    private var _binding1: FragmentHomeBinding? = null
    private val binding get() = _binding1!!

    private val filmsDataBase = listOf(
        Film(
            getString(R.string.title_venom),
            R.drawable.venom,
            getString(R.string.description_venom)
        ),
        Film(
            getString(R.string.title_beekeeper),
            R.drawable.beekeeper,
            getString(R.string.description_beekeeper)
        ),
        Film(
            getString(R.string.title_despicable_me),
            R.drawable.despicable_me4,
            getString(R.string.description_despicable_me)
        ),
        Film(
            getString(R.string.title_alien),
            R.drawable.alien,
            getString(R.string.description_alien)
        ),
        Film(
            getString(R.string.title_puzzle),
            R.drawable.puzzle2,
            getString(R.string.description_puzzle)
        ),
        Film(
            getString(R.string.title_bad_guys),
            R.drawable.the_bad_guys_to_the_end,
            getString(R.string.discription_bad_guys)
        ),
        Film(
            getString(R.string.title_planet_of_the_apes),
            R.drawable.planet_of_the_apes,
            getString(R.string.discription_planet_of_the_apes)
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding1 = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding1 = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.topAppBar.setOnMenuItemClickListener() {
//            when (it.itemId) {
//                R.id.settings -> {
//                    Toast.makeText(this, "Настройки", Toast.LENGTH_SHORT).show()
//                    true
//                }
//
//                else -> false
//            }
//        }

        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(object : FilmListRecyclerAdapter.OnItemClickListener {
                    override fun click(film: Film) {
                        (requireActivity() as MainActivity).launchDetailsFragment(film)
                    }
                })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
        }
        filmsAdapter.addItems(filmsDataBase)
    }
}