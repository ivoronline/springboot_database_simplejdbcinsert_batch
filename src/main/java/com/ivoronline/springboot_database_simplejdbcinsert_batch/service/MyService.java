package com.ivoronline.springboot_database_simplejdbcinsert_batch.service;

import com.ivoronline.springboot_database_simplejdbcinsert_batch.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import java.sql.Types;
import java.util.*;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //==========================================================
  // INSERT1
  //==========================================================
  public int[] insert1(List<PersonDTO> dtoList) {

    //CONFIGURE INSERT STATEMENT
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("PERSON")
      .usingGeneratedKeyColumns("ID");

    //SET PARAMETERS
    List<Map<String, Object>> recordsList = new ArrayList<>();  //LinkedList
    for (PersonDTO dto : dtoList) {
      Map<String, Object> record = new HashMap<>();
                          record.put("NAME", dto.getName());
                          record.put("AGE" , dto.getAge() );

      recordsList.add(record);
    }

    //CONVERT LIST TO ARRAY
    SqlParameterSource[] recordsArray = SqlParameterSourceUtils.createBatch(recordsList);

    //RETURN SUCCESS
    return insert.executeBatch(recordsArray);

  }

  //==========================================================
  // INSERT2
  //==========================================================
  public int[] insert2(List<PersonDTO> dtoList) {

    //CONFIGURE INSERT STATEMENT
    SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
      .withTableName("PERSON")
      .usingGeneratedKeyColumns("ID");

    //SET PARAMETERS
    List<SqlParameterSource> recordsList = new ArrayList<>();
    for (PersonDTO dto : dtoList) {
      MapSqlParameterSource record = new MapSqlParameterSource()
        .addValue("NAME", dto.getName(), Types.NVARCHAR)
        .addValue("AGE" , dto.getAge() , Types.INTEGER);
      recordsList.add(record);
    }

    //CONVERT LIST TO ARRAY
    SqlParameterSource[] recordsArray = recordsList.toArray(new SqlParameterSource[recordsList.size()]);

    //RETURN SUCCESS
    return insert.executeBatch(recordsArray);

  }
}
