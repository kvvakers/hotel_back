package ua.nure.HotelAPI.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@JsonInclude (JsonInclude.Include.NON_DEFAULT)
@Table (name = "city")
public class City {
    @Id
    @Column (name = "cityname", unique = true, updatable = false)
    private String cityName;

    public String getName() {
        return cityName;
    }

    public void setName(String cityName) {
        this.cityName = cityName;
    }
}
