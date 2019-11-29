package com.example.demo.Reposetory.Dbhelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class dbhelper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private Connection connection;

    @Value("${spring.datasource.url}")
    private String springDatasourceUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    public Connection createConnection() {
        try {
            log.info("CreateConnection: trying to get connection");
            connection = DriverManager.getConnection(springDatasourceUrl, dbUsername, dbPassword);
            log.info("CreateConnection: Connection complete");
        } catch (SQLException e) {
            log.info("CreateConnection: Fangede SQLException");
            e.printStackTrace();
        }
        log.info("CreateConnection: Returning connection");
        return connection;
    }

    public void close() {
        log.info("Closing connection...");

        try {
            connection.close();

            log.info("MySQL connection closed...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

