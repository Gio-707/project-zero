package projZero

import java.sql.DriverManager
import java.sql.Connection

object DBconnect{
   // manually loading driver
   classOf[org.postgresql.Driver].newInstance()
   // values for or fields so no retype everytime connect opened, not sure if better way
   // to do this, but it seems like a good idea to open and close connection in same method.
   val dburl = "jdbc:postgresql://localhost:5026/projectzero"
   val dbun = "postgres"
   val dbpw = "password"
   
   
   // pretty crude function to view employee info by running select * from employees on db and printing results
   def viewEmployeeInfo(): Unit = { 
      

       //use JDBC's DriverManager to get a connection.  JDBC is DB agnostic
       //getConnection takes 3 arguments.  I'm going to hardcode creds for now, since this is local
       // postgres is my username and wasspord is my password
       val conn = DriverManager.getConnection(dburl, dbun, dbpw)

       val statement = conn.createStatement()
       val resultSet = statement.executeQuery("SELECT * FROM employees order by employee_id;")
       while(resultSet.next){
          println("employee id = " + resultSet.getString("employee_id") +" ||employee phone = " + resultSet.getString("phone") + 
                   " ||employee first name = " + resultSet.getString("f_name") + " ||employee last name = " + resultSet.getString("l_name"))
       }
       conn.close
   }
   // will attempt to create an add employee method
   // to add an employee in db will use "insert into employees (phone, l_name, f_name)"
   //     where phone name1, name2 provided by user               (phone1,name1,name2)"  
   // will get phone, l_name, f_name from user/ csv, do user method in cli, csv in file
   def addNewEmployee(phone:String, l_name:String, f_name:String): Unit = {
      val conn = DriverManager.getConnection(dburl, dbun, dbpw)
      val statement = conn.createStatement()

      val sqlUpdate = "INSERT INTO EMPLOYEES (phone,l_name,f_name) " + "\nvalues" +
                      "('" + phone + "', '" + l_name + "', '" + f_name + "');"
      statement.execute(sqlUpdate)
      conn.close

      //up and running!
   }

   // method to remove employee, will remove by id
   def removeEmployee(employee_id:Int): Unit = {
      val conn = DriverManager.getConnection(dburl, dbun, dbpw)
      val statement = conn.createStatement()
      val sqlDelete = "delete from employees where employee_id  = " + employee_id + ";"
      statement.execute(sqlDelete)
      conn.close
   }
   // method to view schedule
   def viewSchedule(): Unit ={
       val conn = DriverManager.getConnection(dburl, dbun, dbpw)

       val statement = conn.createStatement()
       val resultSet = statement.executeQuery("SELECT time_id, schedule.employee_id, l_name, \"date\", \"start\", \"end\" FROM employees RIGHT JOIN schedule ON employees.employee_id = schedule .employee_id order by time_id;")
       while(resultSet.next){
          println("time id = " + resultSet.getString("time_id") +" ||employee_id = " + resultSet.getString("employee_id") + 
                   " ||employee last name = " + resultSet.getString("l_name") + " ||date = " + resultSet.getString("date")
                   + "|| start time = " + resultSet.getString("start") + "||end time = " + resultSet.getString("end"))
       }
       conn.close
   }
   // method to update employee, will update based on id
   def updateEmployee(employee_id:Int, phone:String, f_name:String, l_name:String): Unit ={
      val conn = DriverManager.getConnection(dburl, dbun, dbpw)
      val statement = conn.prepareStatement("update employees set phone = ?, f_name = ?, l_name =? where employee_id = ?;")
      statement.setString(1, phone)
      statement.setString(2, f_name)
      statement.setString(3, l_name)
      statement.setInt(4, employee_id)
      statement.execute()
      conn.close

   }
   // method will only allow to change which employee works on a given day, must choose day to change based
   // on column 'time_id', then change the employee by changing the employee_id number to a different number
   def changeSchedule(time_id: Int, employee_id: Int): Unit ={
      val conn = DriverManager.getConnection(dburl, dbun, dbpw)
      val statement = conn.prepareStatement("update schedule set employee_id = ? where time_id = ?;")
      statement.setInt(1, employee_id)
      statement.setInt(2, time_id)
      statement.execute()
      conn.close
   }

}

    
   