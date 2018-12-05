package webcrawler


object WebCrawler extends App {

  val wbService = new WebCrawlerService(new JsoupConnector())

  val intialPage = wbService.getLinksPage(Constants.URL,None)

  wbService.loopPages(intialPage)

  println(wbService.mutablePagesList.head.toString)
}


