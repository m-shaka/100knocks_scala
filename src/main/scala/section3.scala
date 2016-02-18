import argonaut.CodecJson
import argonaut.Argonaut._
import scala.io._

object Section3 {
  // argonautのドキュメントを見よう見まねで
  case class Article(title: String, text: String)

  implicit def ArticleCodecJson: CodecJson[Article] =
    casecodec2(Article.apply, Article.unapply)("title", "text")

  val source =
    Source.fromFile("data/jawiki-country.json", "UTF-8")
    .getLines
    .map(_.decodeOption[Article].orNull)

  // 21以降は20で抽出した記事に対して実行する
  val britishArticles =
    source.collect{
      case article if article.title == "イギリス" => article.text
    }

  def p20() =
    source.collect{
      case article if article.title == "イギリス" => article.text
    }.foreach{println}


  def p21() = {
    britishArticles.next
    .split("\n")
    .filter{_.startsWith("[[Category:")}
    .foreach{println}
  }
  def main(args: Array[String]) = {
    p21()
  }
}
