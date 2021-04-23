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
    //need phone, last name, first name in that order for method
    def addEmployee(): Unit ={
        println() // added these for readibility, will do for each method
        println("To add a new employee, please enter the employees first name.")
        var f_name = StdIn.readLine()
        println("Now please enter the employees last name.")
        var l_name = StdIn.readLine()
        println("Finally, please enter the employees phone number with the format ###-###-####")
        var phone = StdIn.readLine()
        DBconnect.addNewEmployee(phone,l_name,f_name)
        println("Employee succesfully added! the new employee list is:")
        DBconnect.viewEmployeeInfo()
        println()
    }
    // removes employee based on id number
    def removeEmployee: Unit ={
        println() 
        println("To remove an employee, enter that employees id as listed on the table.")
        DBconnect.viewEmployeeInfo()
        println()
        println("Please enter the desired id.")
        var employee_id = StdIn.readInt()
        DBconnect.removeEmployee(employee_id)
        println("Employee susccesfully removed! The updated list is:")
        DBconnect.viewEmployeeInfo()
        println()

    }
    // will update employee, for simplicities sake, updates every field so unchanged fields must be reentered
    def updateEmployee: Unit ={
        println()
        println("Enter the id of employee that needs to be updated from the following table:")
        DBconnect.viewEmployeeInfo()
        println()
        println("please enter the id now")
        var employee_id = StdIn.readInt()
        println("enter the updated first name.")
        var f_name = StdIn.readLine()
        println("enter updated last name")
        var l_name = StdIn.readLine()
        println("enter updated phone number")
        var phone = StdIn.readLine()
        DBconnect.updateEmployee(employee_id,phone,f_name,l_name)
        println("Success! Updated list:")
        DBconnect.viewEmployeeInfo()
        println()

    }
     // only implemented limited capabiltiy, will change row decided by column id, then change who is working by changing the employee id
    def changeSchedule: Unit ={
        println()
        println("Hello, please enter the time id for the day you wish to change:")
        DBconnect.viewSchedule()
        var time_id = StdIn.readInt()
        println()
        println("Enter the id of the employee you want to work instead from the following list:")
        DBconnect.viewEmployeeInfo()
        var employee_id = StdIn.readInt()
        DBconnect.changeSchedule(time_id, employee_id)
        println("Changes saved, new schedule:")
        DBconnect.viewSchedule()
        println()

    }
    
    def uploadFile: Unit ={
        println()
        println("Enter the name of one of the .csv files to upload it to our employees.")
        println("Available files:")
        FileUtil.getTopLevelFileNames().foreach(println)
        var filename = StdIn.readLine()
        FileUtil.addCSVtoBase(FileUtil.getTextContent(filename))
        println("Success! our new employees list:")
        DBconnect.viewEmployeeInfo()
        println()
    }
    // shows employees
    def viewInfo: Unit = {
        println()
        println("The following list contans all current employees.")
        DBconnect.viewEmployeeInfo()
        println()
    }
    // shows schedule
    def viewSchedule: Unit = {
        println()
        println("This is the schedule for the next two weeks.")
        DBconnect.viewSchedule()
        println()
    }
    


}