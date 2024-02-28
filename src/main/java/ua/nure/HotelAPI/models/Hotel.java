package ua.nure.HotelAPI.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "hotel")
public class Hotel {
    @Id
    @Column (name = "hotelid", unique = true, updatable = false)
    private Integer hotelId;
    @Column (name = "hotelname", unique = false, updatable = false)
    private String hotelName;

    @Column (name = "rating", unique = false, updatable = false)
    private Integer rating;

    @Column (name = "cityname", unique = false, updatable = false)
    private String cityName;

    @Column (name = "ownerid", unique = false, updatable = false)
    private Integer ownerId;

    @Column (name = "description", unique = false, updatable = false)
    private String desctiption;

    public Integer getHotelId() { return hotelId; }
    public String getHotelName() { return hotelName; }
    public Integer getRating() { return rating; }
    public String getCityName() { return cityName; }
    public Integer getOwnerId() { return ownerId; }
    public String getDescription() { return desctiption; }
    public void setHotelId(Integer hotelId) { this.hotelId = hotelId; }
}
