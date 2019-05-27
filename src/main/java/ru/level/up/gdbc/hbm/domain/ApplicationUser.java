package ru.level.up.gdbc.hbm.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.level.up.gdbc.hbm.SessionFactoryInitializer;
import java.lang.String;

import javax.persistence.*;

@Entity
@Table(name = "users")

public class ApplicationUser {


    @Id
    @GeneratedValue
    private Integer id;


    @Column(name = "login", unique = true, nullable = false)
    private  String userLogin;


    private String password;

   public ApplicationUser(){}

   public ApplicationUser(String userLogin){
       this.userLogin = userLogin;
   }

    public ApplicationUser ( String userLogin, String password) {
        this.userLogin = userLogin;
        this.password = password;
    }

    public String toStringMy(String string){
      string.replaceAll("[^0-9]", "");
      return string;
      }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
       return id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getPassword() {
        return password;
    }




    }




