package lectures.part2oop

object Objects extends App {

  //Scala does not have class-level functionality ("static")
  //COMPANIONS - BEGIN
  object Person { //type + its only instance
    //"static"/"class" - level functionality
    val N_EYES = 2
    def canFly= false
    //Factory method
    def apply(mother: Person, father: Person) = new Person("Bobbie")
  }
  class Person(val name: String) {
    //instance-level functionality
  }
  //COMPANIONS - END -> Classes with the same name in the same scope
  println(Person.N_EYES)
  println(Person.canFly)
  //Scala Object = Singleton Instance
  val mary = new Person("mary")
  val john = new Person("John")
  println("Is the same instance=> " + (mary == john))

  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  println(Person(mary, john).name)

  //Scala applications = Scala objects with
  //def main(args: Array[String]): Unit

}
