package Kata5;

import java.sql.*;
import java.util.Objects;

public class Employee implements EmployeeLoad,ReadDatabase,SearchEmployee {
    private String Employeeid;
    private String FirstName;
    private String LastName;
    private String Gender;
    private String Job;

    public Employee(String employeeid, String firstName, String lastName, String gender, String job) {
        Employeeid = employeeid;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        Job = job;
    }

    public String getEmployeeid() {
        return Employeeid;
    }

    public void setEmployeeid(String employeeid) {
        Employeeid = employeeid;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(Employeeid, employee.Employeeid) && Objects.equals(FirstName, employee.FirstName) && Objects.equals(LastName, employee.LastName) && Objects.equals(Gender, employee.Gender) && Objects.equals(Job, employee.Job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Employeeid, FirstName, LastName, Gender, Job);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Employeeid='" + Employeeid + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Job='" + Job + '\'' +
                '}';
    }

    @Override
    public void employeeload(Employee e) {
        employees.add(e);
    }

    @Override
    public void reademployeedatabase() throws SQLException {
        String url = "jdbc:sqlite:src/main/resources/employees.sqlite";
        Connection c = DriverManager.getConnection(url);
        Statement s = c.createStatement();
        ResultSet lecture = s.executeQuery("SELECT*FROM Employees2");
        while(lecture.next()){
            employeeload(new Employee(lecture.getString("EmployeeId"),
                    lecture.getString("FirstName"),lecture.getString("LastName"),
                    lecture.getString("Gender"),lecture.getString("Job")));
        }
    }

    @Override
    public Employee search(String id) {
        for(Employee e : employees){
            if(e.getEmployeeid().equals(id)){
                return e;
            }
        }
        return null;
    }
}
