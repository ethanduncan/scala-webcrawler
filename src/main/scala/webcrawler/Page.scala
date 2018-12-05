package webcrawler

import scala.collection.mutable.ListBuffer

/*
  Page takes a url and can have a list of images, externalLinks and children and Optional Page.
  It provides methods to add children Pages to a mutatable list and overriden toString method.
 */
case class Page(url: String,
                images: Seq[String] = Nil,
                externalLinks: Seq[String] = Nil,
                children: Seq[String] = Nil,
                parent: Option[Page] = None) {

  val childrenPage: ListBuffer[Page] = ListBuffer[Page]().distinct

  def addChildPage(child: Page): Unit = childrenPage += child
  def getChildrenPage: Seq[Page] = childrenPage.toSeq.distinct

  override def toString(): String = {
    "-" + url + " \nimgs : \n" + images.distinct.mkString("\n") + "\nexternal links : \n" + externalLinks.distinct.mkString("\n") + "\n" + printChildren()
  }

  def printChildren() = {
    "-" + getChildrenPage.distinct.mkString("---")
  }
}
