package ch.zli.m223.controller;
import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;
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

@Path("/users")
public class UserController {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User create(User user){
        return userService.createUser(user);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> index(){
        return userService.findAll();
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") Long id){
        userService.deleteUser(id);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User update(@PathParam("id") Long id, User user){
        return userService.updateUser(id, user);
    }

}
