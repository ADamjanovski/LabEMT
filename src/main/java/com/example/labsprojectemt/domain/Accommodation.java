package com.example.labsprojectemt.domain;

import com.example.labsprojectemt.domain.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Accommodation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private int numRooms;

    public Accommodation() {
    }

    public Accommodation(String name, String category, Host host, int numRooms) {
        this.name = name;
        this.category = Category.valueOf(category);
        this.host = host;
        this.numRooms = numRooms;
    }

    public void setCategoryByName(String name){
        category=Category.valueOf(name);
    }
    public String getName(){
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }
}
