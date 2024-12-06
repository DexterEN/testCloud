package kth.lab2_journal_core.data.patient;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kth.lab2_journal_core.data.encounter.Encounter;
import kth.lab2_journal_core.data.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "T_Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email") // Map by email
    private User user;

    @Column(nullable = true)
    private LocalDateTime dateOfBirth; // Automatically handled as TIMESTAMP in MySQL

   @OneToMany(mappedBy = "patient")
   @JsonIgnore
    private List<Encounter> encounters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(List<Encounter> encounters) {
        this.encounters = encounters;
    }

}
