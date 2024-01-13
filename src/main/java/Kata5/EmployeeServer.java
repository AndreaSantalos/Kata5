package Kata5;

import static spark.Spark.*;
public class EmployeeServer {
    public static void main(String[] args) {
        get("/Business/:id",(req,res)->{
            Employee a = new Employee("100","An","Santalos","female","boss");
            a.reademployeedatabase();
            String id = req.params(":id");
            Employee employee = a.search(id);
            return (employee == null )?"No existe empleado":employee.getFirstName();
        });
    }
}
