package ch.zli.m223.controller;

import ch.zli.m223.model.Category;  
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import java.util.List;


import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.service.CategoryService;

@Path("/categories")
public class CategoryController {
    @Inject
    CategoryService CategoryService;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary ="Create a new Category.", description = "Creates a new Category")
    public Category create(Category category){
        return CategoryService.createCategory(category);
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Categorys.", description = "Returns a list of all Categorys.")
    public List<Category> index(){
        return CategoryService.findAll();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Delete a Category", description = "Deletes a Category")
    public void delete(@PathParam("id") Long id){
        CategoryService.deleteCategory(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category update(@PathParam("id") Long id, Category category){
        return CategoryService.updateCategory(id, category);
    }



}
