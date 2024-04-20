package ua.nure.HotelAPI.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

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
    private String description;

    @Column (name = "image", unique = false, updatable = false)
    private byte[] image;


    private transient  List<Room> roomList = new ArrayList<Room>();

    public Integer getHotelId() { return hotelId; }
    public String getHotelName() { return hotelName; }
    public Integer getRating() { return rating; }
    public String getCityName() { return cityName; }
    public Integer getOwnerId() { return ownerId; }
    public String getDescription() { return description; }

    public int getRoomListLength() { return roomList.size(); }
    public List<Room> getRoomList() { return roomList; }

    public byte[] getImage() { return image; }
    public void setHotelId(Integer hotelId) { this.hotelId = hotelId; }
    public void addItemToRoomList(Room room) {
        roomList.add(room);
    }
}
