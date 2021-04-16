package lectures.functional.programming

object PatternsEverywhere extends App {

  //big idea #1
  try {
    //code
  } catch {
    case e: RuntimeException => "RuntimeException"
    case npe: NullPointerException => "NullPointerException"
    case _=>
  }
  //Catches actually are matches
  /*
  try {
    //code
  } catch(e) {
    match {
      case e: RuntimeException => "RuntimeException"
      case e: NullPointerException => "NullPointerException"
      case _=>
    }
  }
   */
  //big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 //?!
  } yield 10 * x

  //Generators are also based in Pattern Matching
  val tuples = List((1, 2), (1, 2))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  //case classes, :: operators, ...

  //Big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(a)
  //Multiple value definitions based on Pattern Matching
  //All the Power
  val head::tail = list
  println(head)
  println(tail)

  //Big idea #4 - New
  //Partial function based on Pattern Matching
  val mappedList = list.map{
    case v if v % 2 == 0 => v + " is even"
    case 1 => "The one"
    case _=> "Something else"
  } //partial function

  val mappedList2 = list.map {x => x
    match
    {
      case v if v % 2 == 0 => v + " is even"
      case 1 => "The one"
      case _ => "Something else"
    }
  }
  println(mappedList)
}
