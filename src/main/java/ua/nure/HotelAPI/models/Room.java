package ua.nure.HotelAPI.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "room")
public class Room {
    @Id
    @Column(name="roomid", unique = true, updatable = false)
    private Integer roomId;
    @Column(name="bednumbers", unique = false, updatable = false)
    private Integer bedNumbers;
    @Column(name="price", unique = false, updatable = false)
    private Integer price;
    @Column(name="hotelid", unique = false, updatable = false)
    private Integer hotelId;

    public Integer getRoomId() {return roomId;}
    public Integer getBedNumbers() {return bedNumbers;}
    public Integer getPrice() {return price;}
    public Integer getHotelId() {return hotelId;}
}
