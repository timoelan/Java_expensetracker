package ch.zli.m223.controller;
import ch.zli.m223.model.Budget;
import ch.zli.m223.service.BudgetService;
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

@Path("/budgets")
public class BudgetController {

    @Inject
    BudgetService budgetService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Budget create(Budget budget){
        return budgetService.createBudget(budget);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Budget> index(){
        return budgetService.findAll();
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") Long id){
        budgetService.deleteBudget(id);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Budget update(@PathParam("id") Long id, Budget budget){
        return budgetService.updateBudget(id, budget);
    }

}
