package webcrawler

import org.jsoup.{HttpStatusException, Jsoup, UnsupportedMimeTypeException}
import org.jsoup.nodes.Element

/*
  Connector class using Jsoup to connect to the url and return an Optional body.
  Handles exceptions of unexpected http statuses and unsupported mime types.
 */
class JsoupConnector {

  def connect(url: String): Option[Element] = {
    try {
      val body = Jsoup.connect(url).get.body()
      Some(body)
    } catch {
      case e: HttpStatusException => println(e.getStatusCode)
        None
      case e: UnsupportedMimeTypeException => println(e.getUrl)
        None
    }
  }

}