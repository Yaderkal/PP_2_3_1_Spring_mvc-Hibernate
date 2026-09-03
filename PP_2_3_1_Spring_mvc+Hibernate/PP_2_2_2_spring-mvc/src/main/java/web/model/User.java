package web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "sur_name")
    private String surName;

    @Column(name = "mail")
    private String mail;

    public User() {
    }

    public User(String name, String middleName, String surName, String mail) {
        this.name = name;
        this.middleName = middleName;
        this.surName = surName;
        this.mail = mail;
    }

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

    public String getMiddleName() {  // ← getMiddleName()
        return middleName;
    }

    public void setMiddleName(String middleName) {  // ← setMiddleName()
        this.middleName = middleName;
    }

    public String getSurName() {  // ← getSurName()
        return surName;
    }

    public void setSurName(String surName) {  // ← setSurName()
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(surName, user.surName) &&
                Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, middleName, surName, mail);
    }
}