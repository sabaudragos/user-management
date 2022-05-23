package sab.technologies.usermanagementservice;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

@TestConfiguration
public class UserConfig {

    @Bean
    @LiquibaseDataSource
    public DataSource dataSource() {

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:h2:mem:userservice;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false");
        hikariDataSource.setDriverClassName("org.h2.Driver");
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("sa");

        return hikariDataSource;
    }

}
