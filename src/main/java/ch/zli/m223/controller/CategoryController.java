package ch.zli.m223.controller;
import ch.zli.m223.dto.CategoryDTO;
import ch.zli.m223.model.Category;
import ch.zli.m223.model.User;
import ch.zli.m223.service.CategoryService;
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

@Path("/categories")
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Category create(CategoryDTO dto){
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        User user = new User();
        user.setId(dto.getUserId());
        category.setUser(user);

        return categoryService.createCategory(category);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> index(){
        return categoryService.findAll();
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") Long id){
        categoryService.deleteCategory(id);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category update(@PathParam("id") Long id, Category category){
        return categoryService.updateCategory(id, category);
    }

}
