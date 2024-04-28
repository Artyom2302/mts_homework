package ru.mtsbank.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.mtsbank.entity.Creature;
import ru.mtsbank.service.JDBCService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreatureDAO {
    @Autowired
    JDBCService jdbcService;


    public List<Creature> getCreature() throws SQLException {
        ResultSet resultSet = jdbcService.selectFromCreature();
        List<Creature> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(new Creature(
               resultSet.getInt("id_creature"),
               resultSet.getString("Name"),
               resultSet.getInt("type_id"),
               resultSet.getInt("age"),
               resultSet.getDate("birth_date")
            ));
        }
        return result;
    }
}
