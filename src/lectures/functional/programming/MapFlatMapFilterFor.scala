package lectures.functional.programming

object MapFlatMapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  //Map
  println(list.map(_+1))
  println(list.map(_+ " is a member"))

  //Filter
  println(list.filter(_%2==0))

  //Flatmap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  //Print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "blue", "red", "white")
  //List("a1", "a2", ..."d4")

  //Iterating
  val combinations = numbers.flatMap(x => chars.map(y => x + "" + y))
  println(combinations)
  val combinations2 = numbers.flatMap(n => chars.flatMap(c => colors.map(color => n + "" + c + "_" + color)))
  println(combinations2)
  val combinations3 = numbers.filter(f => f % 2 == 0).flatMap(n => chars.flatMap(c => colors.map(color => n + "" + c + "_" + color)))
  println(combinations3)

  //Foreach
  list.foreach(println)

  //for-comprehensions
  val forCombinations = for {
    n <- numbers
    c <- chars
    color <- colors
  } yield s"$n${c}_$color"
  println(forCombinations)

  val forCombinationsFilters = for {
    n <- numbers if n % 2 == 0
    c <- chars if c == 'd'
    color <- colors if color == "white"
  } yield s"$n${c}_$color"
  println(forCombinationsFilters)

  //foreach == equivalent
  for {
    n <- numbers
  } println(n)

  //Syntax overload
  list.map {
    x => x *2
  }

  /*
    1. MyList supports for-comprehensions
      - map(f: A => B): MyList[B]
      - filter(predicate: A => Boolean): MyList[A]
      - map(f: A => MyList[B]): MyList[B]
    2. A small collection of ONE element - Maybe[+T]
      - map, flatMap, filter
   */
}
