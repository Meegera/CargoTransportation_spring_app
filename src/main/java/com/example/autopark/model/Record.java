package com.example.autopark.model;

import jakarta.persistence.Entity; // аннотация для указания, что класс является сущностью и относится к ORM JPA
import jakarta.persistence.GeneratedValue; // аннотация для работы со столбцами SQL
import jakarta.persistence.GenerationType; // класс, отвечающий за тип данных перечисления
import jakarta.persistence.Id; // аннотация, отвечающая за определение первичного ключа объекта


@Entity
public class Record {
    private Long id;
    private String record_name;
    private String publish_date;
    private String text;
    private String author_name;
    private String link_image;

    public Record() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecord_name() {
        return record_name;
    }

    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getLink_image() {
        return link_image;
    }

    public void setLink_image(String link_image) {
        this.link_image = link_image;
    }

    @Override
    public String toString() {
        return "car [id=" + id + ", record name=" + record_name + ", publish date=" + publish_date + ", text=" + text + ", author name=" + author_name + "]";
    }
}

