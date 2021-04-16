package lectures.part2oop

//import playground._
//import playground.{Cinderella, PrinceCharming}
import playground.{Cinderella, PrinceCharming => Prince}

import java.util.Date
import java.sql.{Date => SQLDate}

object PackagingAndImports extends App2 {
  //Packages members are accessible by their simple name
  val writer = new Writer("Werison", "Fernandes", 1982)

  //Import the package
  val princess = new Cinderella
  val princess2 = new playground.Cinderella //playground.Cinderella => fully qualified name

  //Packages are in hierarchy
  //Matching folder structure

  //Package object
  sayHello
  println(ZERO)

  //Imports
  //val prince = new PrinceCharming
  val prince = new Prince

  //1. Using fully qualified date
  val date = new Date()
  val sqlDate = new java.sql.Date(2020, 1, 2)

  //2. Using aliasing
  val sqlDate2 = new SQLDate(2020, 1, 2)

  //Default imports
  //java.lang => String, objects, Exception
  //scala => Int, Nothing, Function
  //scala.predef => println, ???
}

