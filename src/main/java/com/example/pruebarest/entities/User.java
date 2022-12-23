package com.example.pruebarest.entities;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;

    public User(){

    }

    public User(Long id, String name, String surname, Integer age){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getSurname(){
        return this.surname;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public Integer getAge(){
        return  this.age;
    }

}
