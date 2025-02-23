package exercise;

import exercise.dto.users.UsersPage;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.List;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
//import exercise.dto.users.UsersPage;
import static io.javalin.rendering.template.TemplateUtil.model;
import static java.lang.Integer.parseInt;

import io.javalin.rendering.template.JavalinJte;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users/{id}", ctx -> {
            var id = parseInt(ctx.pathParam("id"));
            var findId = USERS.stream().filter(x -> x.getId() == id).findFirst().orElseThrow( () -> new NotFoundResponse("User not found"));
            var page = new UserPage(findId);
            ctx.render("users/show.jte", model("page", page));

            /*
            int isPresent = -1;
            for (int iter = 0; iter < USERS.size(); iter ++) {
                if (USERS.get(iter).getId() == id) {
                    isPresent = iter;
                    break;
                }
            }
            if ( isPresent >= 0) {
                var page = new UserPage(USERS.get(isPresent));
                ctx.render("users/show.jte", model("page", page));
            } else {
                throw new NotFoundResponse("User not found");
            }
           */
        });

        app.get("/users", ctx -> {
            var page = new UsersPage(USERS);
            ctx.render("users/index.jte", model("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
