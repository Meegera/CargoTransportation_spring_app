package com.example.autopark.model;

import jakarta.persistence.Entity; // аннотация для указания, что класс является сущностью и относится к ORM JPA
import jakarta.persistence.GeneratedValue; // аннотация для работы со столбцами SQL
import jakarta.persistence.GenerationType; // класс, отвечающий за тип данных перечисления
import jakarta.persistence.Id; // аннотация, отвечающая за определение первичного ключа объекта


@Entity
public class Cargo {
    private Long id;
    private String name;
    private String cargo_contents;
    private String city_dispatch;
    private String date_dispatch;
    private String city_arrival;
    private String date_arrival;

    protected Cargo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCargo_contents() {
        return cargo_contents;
    }

    public void setCargo_contents(String cargo_contents) {
        this.cargo_contents = cargo_contents;
    }

    public String getCity_dispatch() {
        return city_dispatch;
    }

    public void setCity_dispatch(String city_dispatch) {
        this.city_dispatch = city_dispatch;
    }

    public String getDate_dispatch() {
        return date_dispatch;
    }

    public void setDate_dispatch(String date_dispatch) {
        this.date_dispatch = date_dispatch;
    }

    public String getCity_arrival() {
        return city_arrival;
    }

    public void setCity_arrival(String city_arrival) {
        this.city_arrival = city_arrival;
    }

    public String getDate_arrival() {
        return date_arrival;
    }

    public void setDate_arrival(String date_arrival) {
        this.date_arrival = date_arrival;
    }

    @Override
    public String toString() {
        return "car [id=" + id + ", name=" + name + ", cargo contents=" + cargo_contents + ", city dispatch=" + city_dispatch + ", date dispatch=" + date_dispatch + ", city arrival=" + city_arrival + ", date arrival=" + date_arrival+ "]";
    }
}
