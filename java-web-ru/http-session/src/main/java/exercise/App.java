package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN

      //  app.get("/users", ctx -> ctx.result("Hello World"));


        app.get("/users", ctx -> {
            var pageNum = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
            var perNum = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);

            List<Map<String, String>> usersList = new ArrayList<>(Data.getUsers());
            List<Map<String, String>> copyUsersList = new ArrayList<>();
           // usersList = Data.getUsers();
            int currentPage = 1;
            int currentPer = 0;

            for (var iter : usersList) {

                if (currentPage == pageNum) {
                    copyUsersList.add(iter);
                }

                currentPer++;
                if (currentPer >= perNum) {
                    currentPage++;
                    currentPer = 0;
                }
            }
            ctx.json(copyUsersList);
        });
     //   app.start(7070); // Стартуем веб-сервер
        
        // END
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
