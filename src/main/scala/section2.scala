import scala.io._
import java.io.PrintWriter

object section2 {

  val source = Source.fromFile("data/hightemp.txt", "UTF-8")

  def p10() = {
    Source.stdin.getLines().length
  }

  def p11() = {
    source.getLines()
    .map(
      _.replace("\t", ",")
    )
    .foreach(println)
  }

  def p12() = {
    val file1 = new PrintWriter("output/" + "col1.txt", "UTF-8")
    val file2 = new PrintWriter("output/" + "col2.txt", "UTF-8")
    source.getLines().map(_.split("\t")).foreach{
      case Array(col1, col2, _*) =>
        file1.println(col1)
        file2.println(col2)
    }

  file1.close()
  file2.close()
  }

  def p13() = {
    val col1 = Source.fromFile("output/col1.txt", "UTF-8").getLines()
    val col2 = Source.fromFile("output/col2.txt", "UTF-8").getLines()

    col1.zip(col2).map(pair => pair._1 + "\t" + pair._2).foreach{println}
  }

  def p14(n: Int) = {
    source.getLines().take(n).foreach{println}
  }

  def p15(n: Int) = {
    val lines = source.getLines()
    lines.drop(lines.length - n).foreach{println}
  }

  def p16(n: Int) = {
    val (lines, lines2) = source.getLines().duplicate
    val size = lines2.length / n
    val writers = (1 to n).map(n => new PrintWriter("output/p16/hightemp" + n + ".txt", "UTF-8"))

    (0 to n-1).foreach(i => lines.take(size).foreach(writers(i).println))
    writers.foreach(_.close)
  }

  def p17() = {
    source.getLines().map(_.split("\t")(0)).toSet
  }

  def p18() = {
    source.getLines().toList.sortBy(_.split("\t")(2).toDouble).reverse.foreach(println)
  }

  def p19() = {
    def howMany(list1: List[String], list2: List[String]): List[(String, Int)] = {
      list2 match {
        case h :: t => (h, list1.count(s => s == h)) :: howMany(list1, t)
        case Nil => Nil
      }
    }

    val (line1, line2) = source.getLines().map(_.split("\t")(0)).duplicate
    howMany(line1.toList, line2.toSet.toList)
    .sortBy(pair => pair._2)
    .reverse
    .foreach(pair => println(pair._1))

  }

  def main(args: Array[String]) = {
    p19()
  }
}
