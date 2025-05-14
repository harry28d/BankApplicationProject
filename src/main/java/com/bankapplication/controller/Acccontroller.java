package com.bankapplication.controller;

import com.bankapplication.dao.Accdao;
import com.bankapplication.model.Acc;
import com.bankapplication.service.Accservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Acccontroller {

    private Accdao accDao = new Accdao();
    private Accservice accService = new Accservice();

    @POST
    public Response createAccount(Acc acc) {
        accDao.saveAccount(acc);
        return Response.status(Response.Status.CREATED).entity(acc).build();
    }

    @GET
    @Path("/{id}")
    public Acc getAccount(@PathParam("id") Long id) {
        return accDao.getAccount(id);
    }

    @POST
    @Path("/{id}/deposit")
    public Response deposit(@PathParam("id") Long id, double amount) {
        accService.deposit(id, amount);
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/withdraw")
    public Response withdraw(@PathParam("id") Long id, double amount) {
        accService.withdraw(id, amount);
        return Response.ok().build();
    }

    @POST
    @Path("/{fromId}/transfer/{toId}")
    public Response transfer(@PathParam("fromId") Long fromId, @PathParam("toId") Long toId, double amount) {
        accService.transfer(fromId, toId, amount);
        return Response.ok().build();
    }
}
