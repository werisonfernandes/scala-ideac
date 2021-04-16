package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */
  case class Person(name: String, age: Int) {

  }

  //1. class parameters are fields
  val person = new Person("Jim", 34)
  println(person.name)

  //2. Sensible toString
  //println(instance) = println(instance.toString) = syntax sugar
  println(person)

  //3. equals and hashCode implement OOTB
  val person2 = new Person("Jim", 34)
  println(person == person2)

  //4. Case classes handy copy methods
  val person3 = person.copy()
  println(person3)

  //5. Case classes have companion objects
  val thePerson = Person
  println(thePerson)
  val mary = Person("Mary", 23)
  println(mary)

  //6. Case classes are serializable
  //Akka

  //7. Case classes have extractor patterns = case classes can be used in Pattern Matching

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
  /*
  Expand MyList to use case classes and case objects
   */
}
