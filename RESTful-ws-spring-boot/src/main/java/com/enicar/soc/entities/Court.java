package com.enicar.soc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id")
    private Long courtId;

    private String name;

    @Column(name = "court_address")
    private Address courtAddress;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courtFolder")
    @JsonIgnore
    private Set<Folder> folders;
    @ManyToMany(cascade = CascadeType.ALL)
    /*@JoinTable(
            name = "court_lawyers",
            joinColumns = @JoinColumn(name = "court_id"),
            inverseJoinColumns = @JoinColumn(name = "lawyer_id")
    )*/
    @JsonIgnore
    private Set<Lawyer> lawyers;

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Embedded
    public Address getCourtAddress() {
        return courtAddress;
    }

    public void setCourtAddress(Address courtAddress) {
        this.courtAddress = courtAddress;
    }

    public Set<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Set<Folder> folders) {
        this.folders = folders;
    }

    public Set<Lawyer> getLawyers() {
        return lawyers;
    }

    public void setLawyers(Set<Lawyer> lawyers) {
        this.lawyers = lawyers;
    }
}
