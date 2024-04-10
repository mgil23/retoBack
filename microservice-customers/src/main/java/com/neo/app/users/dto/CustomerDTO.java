package com.neo.app.users.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class CustomerDTO {


    private Long customerId;
    @NotNull(message = "El password es obligatorio")
    private String password;

    @NotNull(message = "El state es obligatorio")
    private Character state;

    @NotNull(message = "El name es obligatorio")
    @NotEmpty(message = "El name es obligatorio")
    private String name;

    @NotNull(message = "El gender es obligatorio")
    @NotEmpty(message = "El gender es obligatorio")
    private String gender;

    @NotNull(message = "El age es obligatorio")
    private Integer age;

    @NotNull(message = "El identification es obligatorio")
    @NotEmpty(message = "El identification es obligatorio")
    private String identification;

    @NotNull(message = "El address es obligatorio")
    private String address;

    @NotNull(message = "El phoneNumber es obligatorio")
    private String phoneNumber;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
