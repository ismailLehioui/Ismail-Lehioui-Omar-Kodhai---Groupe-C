package com.enicar.soc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends User{

    private Address address;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Lawyer lawyerClient;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientFolder",  orphanRemoval = true)
    @JsonIgnore
    private Set<Folder> folders;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Lawyer getLawyerClient() {
        return lawyerClient;
    }

    public void setLawyerClient(Lawyer lawyerClient) {
        this.lawyerClient = lawyerClient;
    }

    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Set<Folder> folders) {
        this.folders = folders;
    }
}
