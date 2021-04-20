package projZero

import java.io.File
object Main 
{
   def main(args: Array[String]) = {
      //val test1 = new CLI()
     // test1.menu
     var csvcontent = FileUtil.getTextContent("parse_test1.csv")
     FileUtil.addCSVtoBase(csvcontent)
     
   }
     
     
     
   
}