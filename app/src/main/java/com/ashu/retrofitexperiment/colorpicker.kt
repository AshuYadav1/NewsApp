package com.ashu.retrofitexperiment
//singleton
object colorpicker {
    val color = arrayOf(
        "#FFFFFF",
        "#FFFFF0",
        "#FFFFE0",
        "#FFFF00",
        "#FFFAFA",
        "#FFFAF0",
        "#FFF8DC",
        "#FFEFD5",
        "#FFE4E1",
        "#FFD700"
    )

    var colorindex = 1


    // basically when we swipe we called this method it will give us a color one by one when the color get last call the calculation make the index 0 it will repeat again from starting)
    fun getcolor():String{
        return color[colorindex++ % color.size ]
    }

}