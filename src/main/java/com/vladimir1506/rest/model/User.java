package com.vladimir1506.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MyFile> files;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MyFile> getFiles() {
        return files;
    }

    public void setFiles(List<MyFile> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\''
                +", files="
                + files
                ;
    }
}
