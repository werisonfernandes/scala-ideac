package lectures.part2oop

object Inheritance extends App2 {
  println("Works!!!")

  //Single class inheritance
  class Animal {
    val creatureType = "Wild"
    def eat = println("NOM NOM")
  }
  class Cat extends Animal {
    def crunch = {
      eat
      println("CRUNCH CRUNCH")
    }
  }
  val cat =  new Cat
  cat.crunch

  //Constructors
  private class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
    def this() = this("Person")
  }
  private class Adult(name: String, age: Int, idCard: String) extends Person(name, age)

  //Overriding
  final class Pig(override val creatureType: String) extends Animal
  class Cow(cowType: String) extends Animal {
    final override val creatureType: String = cowType
  }
  sealed class Dog extends Animal {
    override val creatureType: String = "Domestic"
    override def eat: Unit = super.eat
  }
  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  //Type substitution (broad: polymorphism)
  val unknownAnimal = new Cow("Red")
  unknownAnimal.eat

  //overriding vs overloading

  //super

  //preventing overriding
  //1. Uses final on member
  //2. Use final on the entire class
  //3. Seal the class => extend class in this file, prevent extension in other files
}
