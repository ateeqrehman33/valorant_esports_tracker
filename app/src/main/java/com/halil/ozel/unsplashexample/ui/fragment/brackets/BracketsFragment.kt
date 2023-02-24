package com.halil.ozel.unsplashexample.ui.fragment.brackets

import android.R.layout
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.DefaultLifecycleObserver
import com.halil.ozel.unsplashexample.R
import com.halil.ozel.unsplashexample.databinding.CustomBracketViewBinding
import com.halil.ozel.unsplashexample.databinding.FragmentBracketsBinding
import com.halil.ozel.unsplashexample.model.brackets.Standing_
import com.ventura.bracketslib.model.ColomnData
import com.ventura.bracketslib.model.CompetitorData
import com.ventura.bracketslib.model.MatchData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_bracket_view.view.*


@AndroidEntryPoint
class BracketsFragment : Fragment(), DefaultLifecycleObserver {


    private lateinit var binding: FragmentBracketsBinding
    private val viewModel: BracketsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val baseInflater = LayoutInflater.from(requireActivity()) // NOT context
        binding = FragmentBracketsBinding.inflate(baseInflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupData()

    }

    private fun setupData() {

        viewModel.responseImages.observe(requireActivity()) { standings ->
            if (standings != null) {

                updateBracket(standings)

            }
        }
    }

    private fun updateBracket(standings: List<Standing_>) {
        //val player10 = CompetitorData("Player 10", "2")


        val columnDataList: ArrayList<ColomnData> = arrayListOf()


        println("columninit size"+standings[0].stages[0].sections[0].columns.size)

        for(i in standings[0].stages[0].sections[0].columns.indices){

            val matchdatalist: ArrayList<MatchData> = arrayListOf()


            for (j in standings[0].stages[0].sections[0].columns[i].cells[0].matches.indices){
                val teamDataA = CompetitorData(standings[0].stages[0].sections[0].columns[i].cells[0].matches[j].teams[0].name,standings[0].stages[0].sections[0].columns[i].cells[0].matches[j].teams[0].result?.gameWins.toString(),standings[0].stages[0].sections[0].columns[i].cells[0].matches[j].teams[0].image)
                val teamDataB = CompetitorData(standings[0].stages[0].sections[0].columns[i].cells[0].matches[j].teams[1].name,standings[0].stages[0].sections[0].columns[i].cells[0].matches[j].teams[1].result?.gameWins.toString(),standings[0].stages[0].sections[0].columns[i].cells[0].matches[j].teams[1].image)
                val matchData = MatchData(teamDataA, teamDataB)
                println("adding match data "+standings[0].stages[0].sections[0].columns[i].cells[0].matches[j].teams[0].name+" , "+standings[0].stages[0].sections[0].columns[i].cells[0].matches[j].teams[1].name)
                matchdatalist.add(matchData)

            }

            println("matchelist size"+matchdatalist.size)

            val finalColomn = ColomnData(matchdatalist)
            columnDataList.add(finalColomn)

        }

        println("columnlist size"+columnDataList.size)


        binding.bracketView.setBracketsData(columnDataList)
       // addDymmyData()


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity?.lifecycle?.addObserver(this)
    }

    override fun onDetach() {
        activity?.lifecycle?.removeObserver(this)
        super.onDetach()
    }


//    private fun addDymmyData(){
//        /////////////dummy data///////////////
//        val player1 = CompetitorData("Player 1", "3")
//        val player2 = CompetitorData("Player 2", "1")
//        val player3 = CompetitorData("Player 3", "3")
//        val player4 = CompetitorData("Player 4", "2")
//        val player5 = CompetitorData("Player 5", "4")
//        val player6 = CompetitorData("Player 6", "2")
//        val player7 = CompetitorData("Player 7", "2")
//        val player8 = CompetitorData("Player 8", "2")
//        val player9 = CompetitorData("Player 9", "2")
//        val player10 = CompetitorData("Player 10", "2")
//
//
//        val matchQuarterFinal1 = MatchData(player1, player2)
//        val matchQuarterFinal2 = MatchData(player3, player4)
//        val matchQuarterFinal3 = MatchData(player5, player6)
//        val matchQuarterFinal4 = MatchData(player7, player8)
//        val match1SemiFinal = MatchData(player2, player4)
//        val match2SemiFinal = MatchData(player5, player7)
//        val match3Final = MatchData(player2, player7)
//
//        val quarterFinalColomn = ColomnData(
//            mutableListOf(
//                matchQuarterFinal1,
//                matchQuarterFinal2,
//                matchQuarterFinal3,
//                matchQuarterFinal4
//            )
//        )
//        val semiFinalColomn = ColomnData(mutableListOf(match1SemiFinal, match2SemiFinal))
//        val finalColomn = ColomnData(mutableListOf(match3Final))
//
//
//        binding.root.bracket_view.setBracketsData(
//            mutableListOf(
//                quarterFinalColomn,
//                semiFinalColomn,
//                finalColomn
//            )
//        )
//    }



}