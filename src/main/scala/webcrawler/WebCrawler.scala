package webcrawler

import org.jsoup.{HttpStatusException, Jsoup}
import scala.collection.mutable.ListBuffer

object WebCrawler extends App {

  var mutableList = new ListBuffer[String]()
  var mutablePagesList = new ListBuffer[Page]()

  val URL = "https://wiprodigital.com"

  def getLinks(url: String, parent: Seq[String]): Option[Seq[Page]] = {
    if(!mutableList.contains(url) && url.contains(URL)) {
      println("1. url    =   " + url)
      try {
        val document = Jsoup.connect(url).get()

        val links = document.body().select("a[href]")
        val imagesElements = document.body().select("img[src]")
        val images = for(x <- 0 until imagesElements.size())
          yield imagesElements.get(x).attr("abs:src").toString
        val page = Page(url, parent, images)
        mutableList += url
        mutablePagesList += page

        val res = (for (i <- 0 until links.size()
              if links.get(i).attr("abs:href").contains(URL)
                && !links.get(i).attr("abs:href").contains("#")
                && !mutableList.contains(links.get(i).attr("abs:href")))
        yield {
          val parents = parent:+url
            getLinks(links.get(i).attr("abs:href"), parents)
        }).flatten.flatten

        Some(res)
      } catch {
        case e: HttpStatusException => println(e.getStatusCode)
          None
      }
    } else {
      None
    }
  }
  //  def reverse(page: Page) = {
  //    page.
  //  }
  getLinks(URL, Seq.empty)

  for(l <- mutablePagesList
    .distinct
    .groupBy(_.url)
    .values
    .flatMap(_.headOption)
    .toList
    .sortBy(_.url))
    yield println("\n" + l.toString() + "\n")

}

case class Page(url: String, parents: Seq[String] = Nil, images: Seq[String] = Nil){
  override def toString(): String = {
    val last = if(parents.size>1) parents.last else url
    val d = for(x <- parents.reverse)
      yield x  +  " ->\n"
    "url = " + last + " links = " +  d + url + " images : " + images
  }
}

