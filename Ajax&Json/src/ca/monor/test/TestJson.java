package ca.monor.test;

import ca.monor.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestJson {

    @Test
    public void test1() {
        Person person = new Person();
        person.setName("dal");
        person.setAge(29);
        person.setGender("M");

        //1. 创建解析器对象
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("123.txt"), person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Person person = new Person();
        person.setName("dal");
        person.setAge(29);
        person.setGender("M");
        person.setBirthday(new Date());

        //1. 创建解析器对象
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("123.txt"), person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        Person person1 = new Person();
        person1.setName("dal1");
        person1.setAge(29);
        person1.setGender("M");
        person1.setBirthday(new Date());

        Person person2 = new Person();
        person2.setName("dal2");
        person2.setAge(29);
        person2.setGender("M");
        person2.setBirthday(new Date());

        Person person3 = new Person();
        person3.setName("dal3");
        person3.setAge(29);
        person3.setGender("M");
        person3.setBirthday(new Date());

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person3);
        list.add(person2);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        Person person1 = new Person();
        person1.setName("dal1");
        person1.setAge(29);
        person1.setGender("M");
        person1.setBirthday(new Date());

        Person person2 = new Person();
        person2.setName("dal2");
        person2.setAge(29);
        person2.setGender("M");
        person2.setBirthday(new Date());

        Person person3 = new Person();
        person3.setName("dal3");
        person3.setAge(29);
        person3.setGender("M");
        person3.setBirthday(new Date());

        Map<String, Object> map = new HashMap<>();
        map.put("name", person1.getName());
        map.put("age", person1.getAge());
        map.put("gender", person1.getGender());
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(map));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * json 转 java
     */
    public void test5() {
        String json = "{\"gender\":\"M\",\"name\":\"dal1\",\"age\":29}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            Person person = mapper.readValue(json, Person.class);
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6() {

    }
}
