package iuh.fit.nguyenhoang_ontap.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Serializable {
    private int id;
    private String name;
    private String role;
    private double salary;

    private int department;
    private int poitsion;
   


    public Employee() {}


    public Employee(int id, String name, String role, double salary, int department, int poitsion) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.department = department;
        this.poitsion = poitsion;
    }

    public Employee( String name, String role, double salary, int department, int poitsion) {
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.department = department;
        this.poitsion = poitsion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return department;
    }
    public void setDepartment(int id) {
        this.department = id;
    }

    public int getPoitsion() {
        return poitsion;
    }
    public void setPoitsion(int ps_id) {
        this.poitsion = ps_id;
    }





}
