package ch.zli.m223.controller;
import ch.zli.m223.model.Transaction;
import ch.zli.m223.service.TransactionService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import java.util.List;

@Path("/transactions")
public class TransactionController {

    @Inject
    TransactionService transactionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Transaction create(Transaction transaction){
        return transactionService.createTransaction(transaction);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> index(){
        return transactionService.findAll();
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") Long id){
        transactionService.deleteTransaction(id);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Transaction update(@PathParam("id") Long id, Transaction transaction){
        return transactionService.updateTransaction(id, transaction);
    }

}
