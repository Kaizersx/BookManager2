package net.proselyte.bookmanager.model;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

     @NotEmpty
     @Size(min = 10, message = "minimum 6 digits")
    private String name;

     @Size (min = 5, max = 10, message = "minimum 5 digits and maximum 10")
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
