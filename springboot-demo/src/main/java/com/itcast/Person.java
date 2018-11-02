package com.pinyougou;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@PropertySource(value = {"classpath:person.properties"})
@PropertySources(
        @PropertySource(value = {"classpath:person.properties",
                                "classpath:person1.properties"}
                 ))
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private boolean sex;
    private int age;
    private Map<String,String>map;
    private List<String>list;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", map=" + map +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
