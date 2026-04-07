package buteco.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;

public class FlyWayconfig {
    public static void migrate(){
        Dotenv dotenv = Dotenv.load();

        Flyway flyway = Flyway.configure()
                .dataSource(
                        "jdbc:postgresql://"+dotenv.get("DB_HOST")+":"+dotenv.get("DB_PORT")+"/"+dotenv.get("DB_NAME"),
                        dotenv.get("DB_USER"),
                        dotenv.get("DB_PASSWORD")
                )
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}
