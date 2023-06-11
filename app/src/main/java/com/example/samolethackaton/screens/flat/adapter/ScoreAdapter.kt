package com.example.samolethackaton.screens.flat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.ScorParamDomain
import com.example.samolethackaton.R
import com.example.samolethackaton.databinding.ItemScorBinding
import kotlin.math.roundToInt

class ScoreAdapter(
    private val scoreList : List<ScorParamDomain>
) : RecyclerView.Adapter<ScoreAdapter.ScoreHolder>() {

    private val TAG = ScoreAdapter::class.simpleName

    inner class ScoreHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemScorBinding.bind(item)

        fun bind(score : ScorParamDomain) = with(binding) {

            itemScorTitle.text = score.name

            if (
                (score.name == "Потолок чистовая") || (score.name == "Стена чистовая") || (score.name == "Пол чистовая") ||
                (score.name == "Потолок черновая") || (score.name == "Стена черновая") || (score.name == "Пол черновая") ||
                (score.name == "Двери") || (score.name == "Унитаз") || (score.name == "Раковина") || (score.name == "Ванна") ||
                (score.name == "Отделка окна") || (score.name == "Установленная батарея")) {

                if (score.score == "0.0%") {
                    itemScorValue.text = "0%"
                } else {
                    Log.d(TAG, "BEFORE: " + score.score.toString())
                    val score_ = score.score.replace("%", "").toDouble()
                    Log.d(TAG, "AFTER: " + score_.toString())
                    Log.d(TAG, "******: " + (score_ * 100.0).toString())
                    Log.d(TAG, "ROUND: " + ((score_ * 100.0).roundToInt()).toString())
                    Log.d(TAG, "//////: " + ((score_ * 100.0).roundToInt() / 100.0).toString())
                    val percent = (score_ * 100.0).roundToInt() / 100.0
                    Log.d(TAG, "FINISH: " + percent.toString())
                    itemScorValue.text = (percent * 100).toInt().toString() + "%"
                }

            } else {
                itemScorValue.text = score.score.toString()
            }

//            itemScorValue.text = percent.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_scor,
                parent,
                false
            )
        return ScoreHolder(view)
    }

    override fun getItemCount(): Int {
        return scoreList.size
    }

    override fun onBindViewHolder(holder: ScoreHolder, position: Int) {
        holder.bind(scoreList[position])
    }

}