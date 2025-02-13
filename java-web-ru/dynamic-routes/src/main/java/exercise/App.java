package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    /*
    public static void Otladka() {
        COMPANIES.stream().filter( mma -> mma.get("id").equals("6")).findAny();
        System.out.println(" --- all ---- ");
    }
    */

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var findId = ctx.pathParam("id");
            var dataFind = COMPANIES.stream().filter(flterParam -> flterParam.get("id").equals(findId)).findFirst();
            dataFind.ifPresent(ctx::json);
            dataFind.orElseThrow( () -> new NotFoundResponse("Company not found"));



            //COMPANIES.stream().filter(mma -> mma.get("id").equals(findId)).findFirst().ifPresent(ctx::json);

            /*

            boolean isFounded = false;

            for (var iter : COMPANIES) {
                if (iter.get("id").equals(findId)) {   // если найти нужный id
                    ctx.json(iter);
                    isFounded = true;
                }
            }
            if (!isFounded) throw new NotFoundResponse("Company not found");

             */
        });
        
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
