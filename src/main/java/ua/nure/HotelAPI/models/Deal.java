package ua.nure.HotelAPI.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "deal")
public class Deal {
    @Id
    @Column(name="dealid", unique = true, updatable = false)
    private Integer dealId;
    @Column(name="datebegin", unique = false, updatable = false)
    private Date dateBegin;
    @Column(name="dateend", unique = false, updatable = false)
    private Date dateEnd;
    @Column(name="total", unique = false, updatable = false)
    private Integer total;
    @Column(name="roomid", unique = false, updatable = false)
    private Integer roomId;
    @Column(name="email", unique = false, updatable = false)
    private String email;
    public Integer getDealId() {return dealId;}
    public Date getDateBegin() {return dateBegin;}
    public Date getDateEnd() {return dateEnd;}
    public Integer getTotal() {return total;}
    public Integer getRoomId() {return roomId;}
    public String getEmail() {return email;}
}
