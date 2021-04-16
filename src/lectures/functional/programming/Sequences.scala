package lectures.functional.programming

import scala.util.Random

object Sequences extends App {
  //Sequence
  val aSequence = Seq(1, 2, 3, 4, 5, 6, 7, 8)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(11, 10, 9, 21))
  println(aSequence.sorted)

  //Ranges
  val ranges: Seq[Int] = 1 until 10
  ranges.foreach(println)
  val ranges2: Seq[Int] = 1 to 10
  ranges2.foreach(println)
  (1 to 10).foreach(println)

  //Lists
  val aList = List(1, 2, 3, 4)
  val prepended = 42 :: aList
  println(prepended)
  val prepended2 = 42 +: aList :+ 89
  println(prepended2)

  val apple5 = List.fill(5)("Apple")
  println(apple5)
  println(aList.mkString("_|_"))

  //Arrays
  val numbers = Array(1, 2, 3)
  println(numbers.foreach(println))
  val threeElements = Array.ofDim[Int](3)
  println(threeElements)
  threeElements.foreach(println)

  //Mutation
  numbers(2) = 0 //Syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  //Arrays and Seq (WrappedArray)
  val numbersSeq: Seq[Int] = numbers //implicit conversion
  println(numbersSeq)

  //Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)
  //Vector vs Lists
  val maxRuns = 1000
  val maxCapacity = 10000000
  def getWriteTime(collection: Seq[Int]): Double= {
    val random = new Random
    val times = for {
      time <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      //Operation
      collection.updated(random.nextInt(maxCapacity), random.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }
  //Test
  //keeps reference to tail
  //updating an element in the middle takes long
  val numbersList = (1 to maxCapacity).toList
  //depth of the tree is small
  //needs to replace an entire 32-element chunk
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))
}
