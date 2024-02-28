package ua.nure.HotelAPI.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "owner")
public class Owner {
    @Id
    @Column (name = "ownerid", unique = true, updatable = false)
    private Integer ownerId;
    @Column (name = "userid", unique = true, updatable = false)
    private Integer userId;

    public Integer getOwnerId() {return ownerId;}
    public Integer getUserId() {return userId;}

}
