package com.denispotapov.calculationofthecostofloghouse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.denispotapov.calculationofthecostofloghouse.databinding.ActivityMainBinding
import com.denispotapov.calculationofthecostofloghouse.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java).also {
            binding.viewmodel = it
        }

        with(binding) {
            buttonResultWalls.setOnClickListener {
                getWallResult()
            }

            buttonResultPartitions.setOnClickListener {
                getPartitionsResult()
            }

            buttonResultOverall.setOnClickListener {
                getOverallResult()
            }
        }
    }

    private fun getWallResult() {

        when (binding.rgSection.checkedRadioButtonId) {
            R.id.rb_80 -> viewModel.onButtonResultWallsClick(
                PRICE_SECTION_80,
                SECTION_80,
                WEIGHT_SECTION_80
            )
            R.id.rb_100 -> viewModel.onButtonResultWallsClick(
                PRICE_SECTION_100,
                SECTION_100,
                WEIGHT_SECTION_100
            )
            R.id.rb_120 -> viewModel.onButtonResultWallsClick(
                PRICE_SECTION_120,
                SECTION_120,
                WEIGHT_SECTION_120
            )
            R.id.rb_140 -> viewModel.onButtonResultWallsClick(
                PRICE_SECTION_140,
                SECTION_140,
                WEIGHT_SECTION_140
            )
        }
    }

    private fun getPartitionsResult() {

        when (binding.rgSectionPartition.checkedRadioButtonId) {
            R.id.rb_80_partition -> viewModel.onButtonResultPartitionsClick(
                PRICE_SECTION_80,
                SECTION_80,
                WEIGHT_SECTION_80
            )
            R.id.rb_100_partition -> viewModel.onButtonResultPartitionsClick(
                PRICE_SECTION_100,
                SECTION_100,
                WEIGHT_SECTION_100
            )
            R.id.rb_120_partition -> viewModel.onButtonResultPartitionsClick(
                PRICE_SECTION_120,
                SECTION_120,
                WEIGHT_SECTION_120
            )
            R.id.rb_140_partition -> viewModel.onButtonResultPartitionsClick(
                PRICE_SECTION_140,
                SECTION_140,
                WEIGHT_SECTION_140
            )
        }
    }

    private fun getOverallResult() {

        val priceWalls = binding.textResultWalls.text.toString()
        val pricePartitions = binding.textResultPartitions.text.toString()

        if (priceWalls.isNotEmpty() && pricePartitions.isNotEmpty()) {
            val priceOverall = priceWalls.toInt() + pricePartitions.toInt()
            binding.textResultOverall.text = priceOverall.toString()
        }

        val amountWoodWalls = binding.textAmountWoodWalls.text.toString()
        val amountWoodPartitions = binding.textAmountWoodPartitions.text.toString()

        if (amountWoodWalls.isNotEmpty() && amountWoodPartitions.isNotEmpty()) {
            val amountWoodOverall = amountWoodWalls.toInt() + amountWoodPartitions.toInt()
            binding.textResultOverallAmountWood.text = amountWoodOverall.toString()
        }

        val weightWalls = binding.textWallsWeight.text.toString()
        val weightPartitions = binding.textPartitionsWeight.text.toString()

        if (weightWalls.isNotEmpty() && weightPartitions.isNotEmpty()) {
            val weightWoodOverall = weightWalls.toInt() + weightPartitions.toInt()
            binding.textResultOverallWeight.text = weightWoodOverall.toString()
        }
    }
}
