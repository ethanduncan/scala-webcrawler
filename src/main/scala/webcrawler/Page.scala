package webcrawler

import scala.collection.mutable.ListBuffer

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
