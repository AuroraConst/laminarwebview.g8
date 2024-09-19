package org.aurora

import scala.scalajs.js
import scala.scalajs.js.annotation.*

import com.raquo.laminar.api.L.{*, given}
import org.scalajs.dom
import scala.scalajs.js.Date
import org.aurora.model.v2.*
import org.aurora.model.v2.utils.{*,given}


@main
def LiveChart(): Unit =
  lazy val rootElement = dom.document.getElementById("root")
  renderOnDomContentLoaded(
    rootElement,
    Main.appElement()
  )
end LiveChart

object Main:


  def appElement(): Element =
    import org.aurora.model.given
    val g = Grid(7,10)
    val g1 = Grid(7,3)
    val g2 = Grid(7,10)

    var firstDate = CalendarGrid(new Date(),7).firstMondayDate.toMidnight
    val dateList = g.linearizedleftRightCoordinates
      .map{_ =>
        val olddate = new Date(firstDate.getTime())
        firstDate.addDays(1)
        olddate
      }
      .toList

     g.populate(dateList) 
     g1.populate(dateList)
     g2.populate(dateList)


    div(
      h1("Chart", img(src:= "/vite.svg")),
      div(child.text <-- g.summaryText,
          child.text <-- g.focusedGridData.map(_.getOrElse("None").toString()) 
      ),
      g.htmlElement,
      div(child.text <-- g1.focusedGridData.map(_.getOrElse("None").toString())),
      g1.htmlElement,
      div(child.text <-- g2.focusedGridData.map(_.getOrElse("None").toString())),
      g2.htmlElement
    )
  end appElement

end Main
