package com.denispotapov.calculationofthecostofloghouse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class MainViewModel : ViewModel() {

    val length = MutableLiveData<Double>()
    val width = MutableLiveData<Double>()
    val height = MutableLiveData<Double>()

    val numberOfDoors = MutableLiveData<Double>()
    val doorLength = MutableLiveData<Double>()
    val doorWidth = MutableLiveData<Double>()

    val numberOfWindows = MutableLiveData<Double>()
    val windowLength = MutableLiveData<Double>()
    val windowWidth = MutableLiveData<Double>()

    val totalLengthOfPartitions = MutableLiveData<Double>()
    val partitionHeight = MutableLiveData<Double>()

    val numberOfDoorsPartition = MutableLiveData<Double>()
    val doorLengthInside = MutableLiveData<Double>()
    val doorWidthInside = MutableLiveData<Double>()

    val check = MutableLiveData(false)

    private val _resultPriceWalls = MutableLiveData<String>()
    val resultPriceWalls: LiveData<String> = _resultPriceWalls

    private val _resultAmountOfWoodWalls = MutableLiveData<String>()
    val resultAmountOfWoodWalls: LiveData<String> = _resultAmountOfWoodWalls

    private val _resultWallsWeight = MutableLiveData<String>()
    val resultWallsWeight: LiveData<String> = _resultWallsWeight

    private val _resultPricePartitions = MutableLiveData<String>()
    val resultPricePartitions: LiveData<String> = _resultPricePartitions

    private val _resultAmountOfWoodPartitions = MutableLiveData<String>()
    val resultAmountOfWoodPartitions: LiveData<String> = _resultAmountOfWoodPartitions

    private val _resultPartitionsWeight = MutableLiveData<String>()
    val resultPartitionsWeight: LiveData<String> = _resultPartitionsWeight

    private val symbols = DecimalFormatSymbols.getInstance()
    private val format = DecimalFormat("0.00")

    fun onButtonResultWallsClick(sectionPrice: Int, section: Double, weight: Int) {
        _resultPriceWalls.value = getPriceWalls(sectionPrice, section, weight)
    }

    fun onButtonResultPartitionsClick(sectionPrice: Int, section: Double, weight: Int) {
        _resultPricePartitions.value = getPricePartitions(sectionPrice, section, weight)
    }

    private fun getPriceWalls(sectionPrice: Int, section: Double, weightSection: Int): String {

        val length = length.value
        val width = width.value
        val height = height.value

        var walls = 0.0
        if (length != null && width != null && height != null) {
            walls = ((2 * length + 2 * width) * ((height / section) + 1)) / 4
        }

        val numberOfWindows = numberOfWindows.value
        val windowLength = windowLength.value
        val windowWidth = windowWidth.value

        var windows = 0.0
        if (numberOfWindows != null && windowLength != null && windowWidth != null) {
            windows = ((numberOfWindows * windowLength * windowWidth) / (4 * section))
        }

        val numberOfDoors = numberOfDoors.value
        val doorLength = doorLength.value
        val doorWidth = doorWidth.value

        var doors = 0.0
        if (numberOfDoors != null && doorLength != null && doorWidth != null) {
            doors = ((numberOfDoors * doorLength * doorWidth) / (4 * section))
        }

        val priceWalls = sectionPrice * (walls - windows - doors)
        val amountWoodForWalls = (priceWalls / sectionPrice)
        val weightWalls = weightSection * (priceWalls / sectionPrice)

        symbols.decimalSeparator = '.'
        format.decimalFormatSymbols = symbols

        _resultAmountOfWoodWalls.value = format.format(amountWoodForWalls)
        _resultWallsWeight.value = format.format(weightWalls)

        return format.format(priceWalls)
    }

    private fun getPricePartitions(sectionPrice: Int, section: Double, weightSection: Int): String {

        val totalLengthOfPartitions = totalLengthOfPartitions.value
        val partitionHeight = partitionHeight.value

        var partitions = 0.0
        if (totalLengthOfPartitions != null && partitionHeight != null) {
            partitions = (totalLengthOfPartitions * partitionHeight) / (4 * section)
        }

        val numberOfDoorsPartition = numberOfDoorsPartition.value
        val doorLengthInside = doorLengthInside.value
        val doorWidthInside = doorWidthInside.value

        var doors = 0.0
        if (numberOfDoorsPartition != null && doorLengthInside != null && doorWidthInside != null) {
            doors = (numberOfDoorsPartition * doorLengthInside * doorWidthInside) / (4 * section)
        }

        val pricePartitions = sectionPrice * (partitions - doors)
        val amountWoodForPartitions = (pricePartitions / sectionPrice)
        val weightPartitions = weightSection * (pricePartitions / sectionPrice)

        symbols.decimalSeparator = '.'
        format.decimalFormatSymbols = symbols

        _resultAmountOfWoodPartitions.value = format.format(amountWoodForPartitions)
        _resultPartitionsWeight.value = format.format(weightPartitions)

        return format.format(pricePartitions)
    }
}