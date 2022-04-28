import java.io.File
import java.io.InputStream


fun main() {
    hangman()
}

fun hangman()
{
    val drawing = drawings()

    var life = 7
    var mistakes = 0
    var guessed = 0

    var isPlayerAlive : Boolean = true
    var wordslist = listOf<String>()
    wordslist = loadWords()

    var usedWords = listOf<Char>()
    var rnumber = (0..wordslist.size).random()
    val word : String = wordslist[rnumber]

    var cenzo = "-".repeat(word.length)


    println(" START GRY! \n Wylosowano słowo o długości " + word.length + "\n Hasło: " + cenzo + "\n Wpisz literę: ")

    while(isPlayerAlive)
    {
        var playerInput = readLine()!!
        usedWords += (playerInput.first())
        println("Wpisana litera: $playerInput")

        for(letter in word) // iteruje po kolei w slowie a nie moze, inaczej to zrob cymbale
        {
            if(word.contains(playerInput)) //jezeli dana litera jest w slowie to dodaje licznik zgadnietych slow oraz odkrywam poszczegolne indeksy w zacenzurowanym slowie
            {
                var count = SearchForLetter(playerInput.first(),word)
                guessed += count.size
                for(i in 0..count.size - 1){
                    var c = StringBuilder(cenzo).also { it.setCharAt(count[i]  ,playerInput.first())}
                    cenzo = c.toString()
                }


                println("-------------------------- \n" + drawing[mistakes] + " \n brawo! \n" + cenzo + "\n" + "Użyte litery: " + usedWords + "\n" + "Życia: " +life + "\n Wpisz literę: ")
                if(guessed == word.length) //jezeli zgadnietych liter jest tyle samo co liter w wyrazie to gra konczy sie wygrana
                {
                    println("BRAWO WYGRAŁEŚ! Wylosowanym słowem było: $word")
                    isPlayerAlive = false
                }
                break

            }
            else if(!(word.contains(playerInput))) //jezeli danej litery nie ma w slowie odejmuje zycie i rysuje kolejna wersje wisielca
            {
                life--
                mistakes++
                if(life == 0)
                {
                    println("PRZEGRAŁEŚ!!! Wylosowanym słowem było: $word")
                    isPlayerAlive = false
                }
                else{
                    println("-------------------------- \n ŹLE \n" + drawing[mistakes] + "\n" + cenzo + "\n" + "Użyte litery: " + usedWords +  "\n" + "Życia: " + life + "\n Wpisz literę: ")
                }
                break
            }
        }


    }
}

fun loadWords() : List<String> //funkcja ktora laduje slowa ze slownika
{
    println(" ładuję słowa ze słownika")
    val inputStream: InputStream = File("C://Users//grzeg//Desktop//slowa.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    println(" załadowano " + lineList.size + " słów")
    return lineList
}

fun drawings() : List<String> //funkcja zwracajaca liste z rysunkami
{
    val d = listOf<String>("  +---+\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "     ===", "  +---+\n" +
            "  O   |\n" +
            "      |\n" +
            "      |\n" +
            "     ===","  +---+\n" +
            "  O   |\n" +
            "  |   |\n" +
            "      |\n" +
            "     ===","  +---+\n" +
            "  O   |\n" +
            " /|   |\n" +
            "      |\n" +
            "     ===","  +---+\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            "      |\n" +
            "     ===","  +---+\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            " /    |\n" +
            "     ===","  +---+\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            " / \\  |\n" +
            "     ===")

    return d

}

fun SearchForLetter(letter: Char, word: String): List<Int> //funkcja szukajaca ilosci i miejsca powtorzen litery w slowie
{
    var list = listOf<Int>()
    for(i in 0..word.length - 1)
    {
        if(letter == word[i])
        {
            list += i
        }
    }
    return list
}


