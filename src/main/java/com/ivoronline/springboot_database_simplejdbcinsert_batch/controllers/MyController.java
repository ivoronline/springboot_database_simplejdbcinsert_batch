package com.ivoronline.springboot_database_simplejdbcinsert_batch.controllers;

import com.ivoronline.springboot_database_simplejdbcinsert_batch.dto.PersonDTO;
import com.ivoronline.springboot_database_simplejdbcinsert_batch.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired private MyService myService;

  //==========================================================
  // INSERT1
  //==========================================================
  @ResponseBody
  @GetMapping("/insert1")
  public int[] insert1() {
    return myService.insert1(createRecords());
  }

  //==========================================================
  // INSERT2
  //==========================================================
  // Request  => http://localhost:8080/insert2?name=Jill&age=40
  @ResponseBody
  @GetMapping("/insert2")
  public int[] insert2() {
    return myService.insert2(createRecords());
  }

  //=========================================================================================================
  // CREATE RECORDS
  //=========================================================================================================
  public List<PersonDTO> createRecords() {

    //CREATE RECORDS TO INSERT
    PersonDTO       person1 = new PersonDTO(0, "Person 1", 10);
    PersonDTO       person2 = new PersonDTO(0, "Person 2", 20);
    PersonDTO       person3 = new PersonDTO(0, "Person 3", 30);
    PersonDTO       person4 = new PersonDTO(0, "Person 4", 40);
    PersonDTO       person5 = new PersonDTO(0, "Person 5", 50);

    //CREATE LIST
    List<PersonDTO> records = new ArrayList<>();
                    records.add(person1);
                    records.add(person2);
                    records.add(person3);
                    records.add(person4);
                    records.add(person5);

    //RETURN LIST
    return records;

  }

}
