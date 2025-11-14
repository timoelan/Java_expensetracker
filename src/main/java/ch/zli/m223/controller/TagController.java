package ch.zli.m223.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.Tag;
import ch.zli.m223.service.TagService;

@Path("/tags")
public class TagController {
    
    @Inject
    TagService tagService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new tag.", description = "Creates a new tag")
    public Tag create(Tag tag){
        return tagService.createTag(tag);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Tags.", description = "Returns a list of all tags.")
    public List<Tag> index(){
        return tagService.findAll();
    } 
    
    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a tag.", description = "Deletes a tag")
    public void delete(@PathParam("id") Long id){
        tagService.deleteTag(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a tag.", description = "Updates an existing tag.")
    public Tag update(@PathParam("id") Long id, Tag tag){
        return tagService.updateTag(id, tag);
    }
}
