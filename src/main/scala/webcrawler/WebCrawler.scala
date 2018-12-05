package webcrawler

/*
  Main class creating needed classes and printing the result.
 */
object WebCrawler extends App {

  val wbService = new WebCrawlerService(new JsoupConnector())

  val intialPage = wbService.getLinksOnPage(Constants.URL,None)

  wbService.loopPages(intialPage)

  println(wbService.mutablePagesList.head.toString)
}


