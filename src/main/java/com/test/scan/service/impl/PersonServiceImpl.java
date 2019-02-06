package com.test.scan.service.impl;

import com.test.scan.dao.PersonDao;
import com.test.scan.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Autowired
    List<PersonDao> list;

    @Autowired
    Set<PersonDao> set;

    @Autowired
    Map<String, PersonDao> map;

}
