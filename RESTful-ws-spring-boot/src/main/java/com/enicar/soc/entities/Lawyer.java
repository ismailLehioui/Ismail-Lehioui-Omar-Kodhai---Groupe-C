package com.enicar.soc.entities;

import com.enicar.soc.enums.Speciality;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "lawyer")
public class Lawyer extends User{

    @Enumerated(EnumType.STRING)
    private Speciality speciality;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lawyerClient", orphanRemoval = true)
    @JsonIgnore
    Set<Client> clients;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lawyerFolder", orphanRemoval = true)
    @JsonIgnore
    Set<Folder> folders;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "lawyers")
    @JsonIgnore
    private Set<Court> courts;





    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }



    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Set<Folder> folders) {
        this.folders = folders;
    }

    public Set<Court> getCourts() {
        return courts;
    }

    public void setCourts(Set<Court> courts) {
        this.courts = courts;
    }
}
