package com.test.ioc.bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Person {

    private String name;
    private Car car;
    private List<Car> list1;
    private List<String> list2;
    private Set<String> set;
    private Map<String, Object> map;
    private Properties properties;

    public Person() {
        System.out.println("Person constructor...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Car> getList1() {
        return list1;
    }

    public void setList1(List<Car> list1) {
        this.list1 = list1;
    }

    public List<String> getList2() {
        return list2;
    }

    public void setList2(List<String> list2) {
        this.list2 = list2;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void hello() {
        System.out.println("hello: " + name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", car=" + car +
                ", list1=" + list1 +
                ", list2=" + list2 +
                ", set=" + set +
                ", map=" + map +
                ", properties=" + properties +
                '}';
    }

    public void init() {
        System.out.println("Person init...");
    }

    public void destroy() {
        System.out.println("Person destroy...");
    }

}
