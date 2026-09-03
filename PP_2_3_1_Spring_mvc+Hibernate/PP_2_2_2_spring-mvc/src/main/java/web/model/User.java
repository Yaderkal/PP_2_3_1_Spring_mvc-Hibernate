package web.model;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "middleName")
    String middleName;
    @Column(name = "surName")
    String surName;
    @Column(name = "mail")
    String mail;

    public User(int id, String name, String middleName, String surName, String mail) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.surName = surName;
        this.mail = mail + "domain.com";
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(middleName, user.middleName) && Objects.equals(surName, user.surName) && Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, middleName, surName, mail);
    }
}
