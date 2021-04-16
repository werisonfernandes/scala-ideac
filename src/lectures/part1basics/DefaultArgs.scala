package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {

  @tailrec
  def factorial(n: Int, accumulator: Int = 1): Int = {
    if(n <= 1) accumulator
    else factorial(n-1, n * accumulator)
  }
  println(factorial(5))

  def savePictures(format: String = ".jpg", width: Int = 1920, height: Int = 1080): Unit = {
    println("Saving picture")
  }
  savePictures(".jpg")
  savePictures(".jpg", 800)
  savePictures(width = 800)
  /*
    1. Pass in every leading argument
    2. Named arguments
   */
}
