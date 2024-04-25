package ru.mtsbank.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.mtsbank.dao.Creature;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class JDBCService {
    private Connection connection = null;
    private PreparedStatement selectStatement = null;

    JDBCService(){}
    @PostConstruct
    void Init() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
        connection.setSchema("animals");
        selectStatement = connection.prepareStatement("select * from creature");
    }
    public ResultSet selectFromCreature() throws SQLException {
        return selectStatement.executeQuery();
    }
    @PreDestroy
    void statementsClose() throws SQLException {
        selectStatement.close();
        connection.close();
    }
}
