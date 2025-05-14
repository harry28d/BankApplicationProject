package com.bankapplication.controller;

import com.bankapplication.dao.Customerdao;
import com.bankapplication.model.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Customercontroller {

    private Customerdao customerDao = new Customerdao();

    @POST
    public Response createCustomer(Customer customer) {
        customerDao.saveCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(customer).build();
    }

    @GET
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") Long id) {
        return customerDao.getCustomer(id);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerDao.deleteCustomer(id);
        return Response.noContent().build();
    }
}
