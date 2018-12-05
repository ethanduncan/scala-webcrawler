package webcrawler

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer


class WebCrawlerService(js: JsoupConnector) {

  var mutablePagesList = new ListBuffer[Page]()

  def getLinksPage(url: String,
                   parent: Option[Page] = None): Option[Page] = {
    (!mutablePagesList.exists(_.url==url),  url.contains(Constants.URL)) match {
      case (true,true) => {
        val jsoupConnection = js.connect(url)
        if(jsoupConnection.isDefined) {
          println(" url    =   " + url)
          val document = jsoupConnection.get
          val elements = document.select("a[href]").toList
          val imagesElements = document.select("img[src]").toList
          val links = elements.map(_.attr("abs:href"))
            .filter(!_.contains("?"))
            .filter(!_.contains("#"))
            .distinct
          val images = imagesElements.map(_.attr("abs:src"))
            .distinct
          val externalLinks = links.filter(!_.contains(Constants.URL)).distinct
          val page =
            Page(url, images, externalLinks, links.filter(_.contains(Constants.URL)), parent
            )
          mutablePagesList += page

          if (parent.isDefined) parent.get.addChildPage(page)
          Some(page)
        } else {
          None
        }
      }
      case (_,false) => None
      case _ => None
    }
  }

  def loopPages(page: Option[Page]): Option[Seq[Page]] = {
    if(page.isDefined) {
      val res = page.get.children.map {
        getLinksPage(_,page)
      }
      if (res.nonEmpty) {
        Some(res.flatMap { x =>
          loopPages(x)
        }.flatten)
      } else {
        None
      }
    }else None
  }

}
