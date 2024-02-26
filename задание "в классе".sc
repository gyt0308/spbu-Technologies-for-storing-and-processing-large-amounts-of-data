//Task a

import scala.io.Source
import scala.util.Using
import java.io.PrintWriter
import java.io.File

object PhoneBook {
  var phoneBook: Map[String, String] = readPhoneBookFromFile.getOrElse(Map())

  def readPhoneBookFromFile: Option[Map[String, String]] = {
    Using(Source.fromFile("phonebook.txt")) { source =>
      source.getLines().map { line =>
        val Array(name, number) = line.split(",")
        name -> number
      }.toMap
    }.toOption
  }

  def savePhoneBookToFile(): Unit = {
    val writer = new PrintWriter(new File("phonebook.txt"))
    phoneBook.foreach(entry => writer.println(s"${entry._1},${entry._2}"))
    writer.close()
  }

  def main(args: Array[String]): Unit = {
    var running = true
    while (running) {
      println("Please select operation：")
      println("0 - Exit")
      println("1 - Add Record (Name and Phone Number)")
      println("2 - Find Phone by Name")
      println("3 - Find Name by Phone Number")
      println("4 -Save current data to file")
      println("5 - Display the contents of the entire phonebook")

      val choice = scala.io.StdIn.readInt()

      choice match {
        case 0 =>
          running = false
        case 1 =>
          println("Please enter your name：")
          val name = scala.io.StdIn.readLine()
          println("Please enter phone number：")
          val number = scala.io.StdIn.readLine()
          phoneBook += (name -> number)
        case 2 =>
          println("Please enter the name you are looking for：")
          val name = scala.io.StdIn.readLine()
          println(phoneBook.getOrElse(name, "No phone number found for this name"))
        case 3 =>
          println("Please enter the phone number you are looking for：")
          val number = scala.io.StdIn.readLine()
          println(phoneBook.find(_._2 == number).map(_._1).getOrElse("No name found for this phone number"))
        case 4 =>
          savePhoneBookToFile()
          println("Phonebook data has been saved to a file")
        case 5 =>
          phoneBook.foreach(entry => println(s"name：${entry._1}  Phone number：${entry._2}"))
        case _ =>
          println("Invalid operation, please re-enter")
      }
    }
  }
}


//Task b

trait Closure {
  def closureFunction(): Unit
}

trait PartiallyAppliedFunction {
  def partiallyAppliedFunction(param: Int): Int
}

trait NestedFunction {
  def nestedFunction(): Unit
}

trait AnonymousFunction {
  def anonymousFunction(): Unit
}

trait FunctionWithVariableArguments {
  def functionWithVariableArguments(args: Int*): Int
}

class SampleClass extends Closure with PartiallyAppliedFunction with NestedFunction with AnonymousFunction with FunctionWithVariableArguments {
  override def closureFunction(): Unit = {
    println("Implementing closure functions")
  }

  override def partiallyAppliedFunction(param: Int): Int = {
    param * 2
  }

  override def nestedFunction(): Unit = {
    def nested(): Unit = {
      println("Nested Functions")
    }
    nested()
  }

  override def anonymousFunction(): Unit = {
    val func = () => println("Anonymous Functions")
    func()
  }

  override def functionWithVariableArguments(args: Int*): Int = {
    args.sum
  }
}