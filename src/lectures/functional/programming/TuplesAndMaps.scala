package lectures.functional.programming

object TuplesAndMaps extends App {
  //Tuples -> finite ordered lists
  val aTuple= new Tuple2(2, "Hello, Scala!") //Tuple2[Int, String] = (Int,String)
  println(aTuple._1) //2
  println(aTuple._2) //Hello, Scala!
  println(aTuple.copy(_2 = "Goodbye Java")) //2
  println(aTuple.swap) //Hello, Scala!

  //Maps -> keys -> values
  val aMap: Map[String, Int] = Map()
  val phoneBook = Map(("Jim", 555), ("Werison", 444), ("JIM", 900)).withDefaultValue(-1)
  //a -> b is a syntax sugar for (a, b)
  println(phoneBook)

  //Basic map operations
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))

  //Add a pairing
  val newPairing = "Mary" -> 888
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  //Functionals in map
  //map, flatMap and filter
  println(newPhoneBook.map(pair => pair._1.toLowerCase() -> pair._2))
  //filterKeys
  println(newPhoneBook.filterKeys(x => x.startsWith("W")))
  //mapValues
  println(newPhoneBook.mapValues(x => "09842-" + x))

  //Conversions to other collections
  println(phoneBook.toList)
  println(phoneBook.toVector)
  println(List(("Werison", 555)).toMap)

  val names = List("Werison", "Jim", "Mary", "John", "Angela", "Wallace", "Ana", "Jim")
  println(names.groupBy(x => x.charAt(0)))
  /*
    1. What would happen if i had two original entries "Jim" -> 555 and "JIM" ->900?
    !!!careful with map keys
    2. Overly simplified social network based on maps
      Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend

        - number of friends of a person
        - person with most friends
        - how many people have NO FRIENDS
        - if there is a social connection between two people (direct or not)
   */
}
