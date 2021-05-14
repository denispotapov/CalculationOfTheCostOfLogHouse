package com.denispotapov.calculationofthecostofloghouse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

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

    // for different windows
    val window1Length = MutableLiveData<Double>()
    val window1Width = MutableLiveData<Double>()

    val window2Length = MutableLiveData<Double>()
    val window2Width = MutableLiveData<Double>()

    val window3Length = MutableLiveData<Double>()
    val window3Width = MutableLiveData<Double>()

    val window4Length = MutableLiveData<Double>()
    val window4Width = MutableLiveData<Double>()

    val window5Length = MutableLiveData<Double>()
    val window5Width = MutableLiveData<Double>()

    val window6Length = MutableLiveData<Double>()
    val window6Width = MutableLiveData<Double>()

    val window7Length = MutableLiveData<Double>()
    val window7Width = MutableLiveData<Double>()

    val window8Length = MutableLiveData<Double>()
    val window8Width = MutableLiveData<Double>()

    val window9Length = MutableLiveData<Double>()
    val window9Width = MutableLiveData<Double>()

    val window10Length = MutableLiveData<Double>()
    val window10Width = MutableLiveData<Double>()

    val checkPartitions = MutableLiveData(false)
    val checkDifferentWindows = MutableLiveData(true)

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

    fun onButtonResultWallsClick(sectionPrice: Int, section: Double, weight: Int) {
        if (checkDifferentWindows.value == true) {
            _resultPriceWalls.value = getPriceWalls(sectionPrice, section, weight)
        } else {
            _resultPriceWalls.value = getPriceWallsWithDiffWindows(sectionPrice, section, weight)
        }
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

        val priceWalls = sectionPrice * (ceil(walls - windows - doors))
        val amountWoodForWalls = (ceil(priceWalls / sectionPrice))
        val weightWalls = weightSection * (priceWalls / sectionPrice)

        _resultAmountOfWoodWalls.value = amountWoodForWalls.toInt().toString()
        _resultWallsWeight.value = weightWalls.toInt().toString()

        return priceWalls.toInt().toString()
    }

    private fun getPriceWallsWithDiffWindows(
        sectionPrice: Int,
        section: Double,
        weightSection: Int
    ): String {

        val length = length.value
        val width = width.value
        val height = height.value

        var walls = 0.0
        if (length != null && width != null && height != null) {
            walls = ((2 * length + 2 * width) * ((height / section) + 1)) / 4
        }

        val window1Length = window1Length.value
        val window1Width = window1Width.value

        var diffWindows1 = 0.0
        if (window1Length != null && window1Width != null) {
            diffWindows1 = ((window1Length * window1Width) / (4 * section))
        }

        val window2Length = window2Length.value
        val window2Width = window2Width.value

        var diffWindows2 = 0.0
        if (window2Length != null && window2Width != null) {
            diffWindows2 = ((window2Length * window2Width) / (4 * section))
        }

        val window3Length = window3Length.value
        val window3Width = window3Width.value

        var diffWindows3 = 0.0
        if (window3Length != null && window3Width != null) {
            diffWindows3 = ((window3Length * window3Width) / (4 * section))
        }

        val window4Length = window4Length.value
        val window4Width = window4Width.value

        var diffWindows4 = 0.0
        if (window4Length != null && window4Width != null) {
            diffWindows4 = ((window4Length * window4Width) / (4 * section))
        }

        val window5Length = window5Length.value
        val window5Width = window5Width.value

        var diffWindows5 = 0.0
        if (window5Length != null && window5Width != null) {
            diffWindows5 = ((window5Length * window5Width) / (4 * section))
        }

        val window6Length = window6Length.value
        val window6Width = window6Width.value

        var diffWindows6 = 0.0
        if (window6Length != null && window6Width != null) {
            diffWindows6 = ((window6Length * window6Width) / (4 * section))
        }

        val window7Length = window7Length.value
        val window7Width = window7Width.value

        var diffWindows7 = 0.0
        if (window7Length != null && window7Width != null) {
            diffWindows7 = ((window7Length * window7Width) / (4 * section))
        }

        val window8Length = window8Length.value
        val window8Width = window8Width.value

        var diffWindows8 = 0.0
        if (window8Length != null && window8Width != null) {
            diffWindows8 = ((window8Length * window8Width) / (4 * section))
        }

        val window9Length = window9Length.value
        val window9Width = window9Width.value

        var diffWindows9 = 0.0
        if (window9Length != null && window9Width != null) {
            diffWindows9 = ((window9Length * window9Width) / (4 * section))
        }

        val window10Length = window10Length.value
        val window10Width = window10Width.value

        var diffWindow10 = 0.0
        if (window10Length != null && window10Width != null) {
            diffWindow10 = ((window10Length * window10Width) / (4 * section))
        }

        val allDiffWindows =
            diffWindows1 + diffWindows2 + diffWindows3 + diffWindows4 + diffWindows5 + diffWindows6 + diffWindows7 + diffWindows8 +
                diffWindows9 + diffWindow10

        val numberOfDoors = numberOfDoors.value
        val doorLength = doorLength.value
        val doorWidth = doorWidth.value

        var doors = 0.0
        if (numberOfDoors != null && doorLength != null && doorWidth != null) {
            doors = ((numberOfDoors * doorLength * doorWidth) / (4 * section))
        }

        val priceWalls = sectionPrice * (ceil(walls - allDiffWindows - doors))
        val amountWoodForWalls = (ceil(priceWalls / sectionPrice))
        val weightWalls = weightSection * (priceWalls / sectionPrice)

        _resultAmountOfWoodWalls.value = amountWoodForWalls.toInt().toString()
        _resultWallsWeight.value = weightWalls.toInt().toString()

        return priceWalls.toInt().toString()
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

        val pricePartitions = sectionPrice * (ceil(partitions - doors))
        val amountWoodForPartitions = (ceil(pricePartitions / sectionPrice))
        val weightPartitions = weightSection * (pricePartitions / sectionPrice)

        _resultAmountOfWoodPartitions.value = amountWoodForPartitions.toInt().toString()
        _resultPartitionsWeight.value = weightPartitions.toInt().toString()

        return pricePartitions.toInt().toString()
    }
}
