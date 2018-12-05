package webcrawler

import org.jsoup.nodes.Document
import org.scalamock.scalatest.MockFactory
import org.scalatest._


class WebCrawlerServiceSpec extends FlatSpec with Matchers with MockFactory {

  val mockJsoup = mock[JsoupConnector]

  "WebCrawlerService" should
    "return an Option Page when supplied with a url" in {
    val testDocument = new Document("https://wiprodigital.com/").append("<a href='https://wiprodigital.com/test'>abc</a>")

    (mockJsoup.connect _).expects(*).returns(Some(testDocument))

    val wcs = new WebCrawlerService(mockJsoup)

    val res = wcs.getLinksOnPage("https://wiprodigital.com/", None)
    res shouldBe an[Option[Page]]
  }

  it should "return None when JsoupConnector returns None" in {

    (mockJsoup.connect _).expects(*).returns(None)

    val wcs = new WebCrawlerService(mockJsoup)

    val res = wcs.getLinksOnPage("https://wiprodigital.com/", None)
    res shouldBe None
  }


  it should "return None when url doesn't include base domain" in {

    val wcs = new WebCrawlerService(mockJsoup)

    val res = wcs.getLinksOnPage("https://test.com/", None)
    res shouldBe None
  }
}
