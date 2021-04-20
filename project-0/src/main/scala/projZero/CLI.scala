package projZero
import scala.io.StdIn
class CLI{
   
    def menu(): Unit ={
       printWelcome
       // boolean to keep looping
       var repeatMenu = true

       while(repeatMenu){
          printOptions
          //object to collect user input
          val input = StdIn.readLine()
          if(input.equalsIgnoreCase("exit"))
             repeatMenu = false
          else if(input.equalsIgnoreCase("add"))
             addEmployee  
          else if(input.equalsIgnoreCase("remove"))
             removeEmployee
          else if(input.equalsIgnoreCase("update"))
             updateEmployee
          else if(input.equalsIgnoreCase("change"))
             changeSchedule
          else if(input.equalsIgnoreCase("upload"))
             uploadFile
          else if(input.equalsIgnoreCase("info"))
             viewInfo
          else if(input.equalsIgnoreCase("schedule"))
             viewSchedule   
          else
             println("Invalid input")
           
         
       }

       
    }
    // welcome to using app
    def printWelcome(): Unit ={
        println("Welcome to employee management system.")
    }
    def printOptions(): Unit ={
        println("Choose one of the following options by typing in the brakceted word.")
        println("View employee [info]")
        println("View employee [schedule]")
        println("[Add] employee")
        println("[Remove] employee")
        println("[Update] employee info")
        println("[Change] shcheudle")
        println("[Upload] file containing employee information")
        println("[Exit] application")
    }

    def addEmployee(): Unit ={
        val empAdd = new dbConnect()
        empAdd.addNewEmployee("707-295-3321", "Reciver", "Booty")
        println("worked?")
    }

    def removeEmployee: Unit ={
        println("remove employee goes here")
    }

    def updateEmployee: Unit ={
        println("update employee goes here")
    }

    def changeSchedule: Unit ={
        println("change sched goes here")
    }
    
    def uploadFile: Unit ={
        println("Enter the name of one of the .csv files to upload it to the database.")
        FileUtil.getTopLevelFileNames().foreach(println)
        println(FileUtil.getTextContent("testngread.csv"))
        
    }
    
    def viewInfo: Unit = {
        val empInf = new dbConnect()
        empInf.viewEmployeeInfo()
    }
    
    def viewSchedule: Unit = {
        println("view shcedule goes here")
    }
    


}