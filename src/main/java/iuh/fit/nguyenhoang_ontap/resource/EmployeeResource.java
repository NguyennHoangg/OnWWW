package iuh.fit.nguyenhoang_ontap.resource;

import iuh.fit.nguyenhoang_ontap.controller.EmployeeService;
import iuh.fit.nguyenhoang_ontap.model.Employee;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/employee")
public class EmployeeResource {
    private EmployeeService employeeService = new EmployeeService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAll() throws SQLException {
        try {
            return Response.ok(employeeService.getAll()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        try {
            Employee employee = employeeService.getById(id);
            if (employee == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee not found with id = " + id)
                        .build();
            }
            return Response.ok(employee).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByName(@QueryParam("name") String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Query param 'name' is required")
                        .build();
            }

            java.util.List<Employee> employees = employeeService.searchByName(name);
            if (employees.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No employee found with name containing: " + name)
                        .build();
            }
            return Response.ok(employees).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmp(@PathParam("id") int id, Employee employeeUpdate){
        try {
            // Lấy employee cũ từ DB
            Employee existing = employeeService.getById(id);
            if (existing == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee not found with id = " + id)
                        .build();
            }
            
            // Update các field mới
            existing.setName(employeeUpdate.getName());
            existing.setRole(employeeUpdate.getRole());
            existing.setSalary(employeeUpdate.getSalary());
            

            
            if(employeeService.updateEmp(existing)){
                return Response.ok(existing).build();
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Update failed")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error: " + e.getMessage())
                    .build();
        }
    }
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertEmp(Employee emp){
        try {
            Employee e = new Employee();
            e.setName(emp.getName());
            e.setRole(emp.getRole());
            e.setSalary(emp.getSalary());
            e.setDepartment(emp.getDepartment());
            e.setPoitsion(emp.getPoitsion());

           if(employeeService.insertEmp(e)){
               return Response.status(Response.Status.CREATED).build();
           }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}
