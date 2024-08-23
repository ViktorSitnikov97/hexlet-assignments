package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();
    private static Map<String, String> result = new HashMap<>();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var id = ctx.pathParam("id");
            List<String> listId = new ArrayList<>();
            COMPANIES.forEach(company -> {
                listId.add(company.get("id"));
            });
            if (!listId.contains(id)) {
                ctx.status(404).result("Company not found");
            } else {
                COMPANIES.forEach(company -> {
                    if (id.equals(company.get("id"))) {
                        result = company;
                    }
                });
                ctx.json(result);
            }
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
