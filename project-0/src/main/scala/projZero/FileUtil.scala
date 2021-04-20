package projZero

import java.io.File
import scala.io.BufferedSource
import scala.io.Source

// will use this class to deal with files

object FileUtil{
    // will get top-level file names in current directore, so save csv's to be processed there
    def getTopLevelFileNames(): Array[String] = {
       val currentDir = new File(".")
       currentDir.listFiles()
       .filter((f:File) => {f.isFile && !f.isHidden()})
       .map(_.getName())
    }

    // retrieves textcontent of file as string
    def getTextContent(filename: String): String = {
        var openedFile: BufferedSource = null
        // throws file not found, will fix using try catch in method that loads doc to database
        try {
            openedFile = Source.fromFile(filename)
            openedFile.getLines().mkString(" ")
        }finally{
            if (openedFile != null) openedFile.close()
        }
    }
    // parses file and adds it to the data base, takes in file converted to string.
    def addCSVtoBase(stringFile: String): Unit ={
       val file_array = stringFile.split("[, ]").map(_.trim)
       val updater = new dbConnect
       var counter = 0
       while(counter != file_array.length){
          var phone_num = file_array(counter)
          counter = counter + 1
          var f_name = file_array(counter)
          counter = counter + 1
          var l_name = file_array(counter)
          counter = counter + 1
          updater.addNewEmployee(phone_num,f_name,l_name)
     }
     
    }
}