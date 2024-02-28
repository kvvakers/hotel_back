package ua.nure.HotelAPI.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "users")
public class User {
    @Id
    @Column (name = "userid", unique = true, updatable = false)
    private Integer userId;
    @Column (name = "status", unique = false, updatable = false)
    private String status;
    @Column (name = "name", unique = false, updatable = false)
    private String name;
    @Column (name = "surname", unique = false, updatable = false)
    private String surname;
    @Column (name = "phone", unique = false, updatable = false)
    private String phone;
    @Column (name = "email", unique = false, updatable = false)
    private String email;
    @Column (name = "password", unique = false, updatable = false)
    private String password;

    public Integer getUserId() { return userId; }
    public String getStatus() { return status; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
