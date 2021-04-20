package projZero

import java.sql.DriverManager
import java.sql.Connection

class dbConnect{
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
       val resultSet = statement.executeQuery("SELECT * FROM employees;")
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
      println(sqlUpdate)                 
      statement.execute(sqlUpdate)
      conn.close

      //up and running!
   }


}

    
   