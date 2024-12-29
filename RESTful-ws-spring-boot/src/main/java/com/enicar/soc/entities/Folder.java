package com.enicar.soc.entities;

import com.enicar.soc.enums.FolderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Long folderId;

    @Column(nullable = false)
    private String name;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private FolderState state;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Client clientFolder;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Lawyer lawyerFolder;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Court courtFolder;

    public Long getFolderId() {
        return folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FolderState getState() {
        return state;
    }

    public void setState(FolderState state) {
        this.state = state;
    }

    public Client getClientFolder() {
        return clientFolder;
    }

    public void setClientFolder(Client clientFolder) {
        this.clientFolder = clientFolder;
    }

    public Lawyer getLawyerFolder() {
        return lawyerFolder;
    }

    public void setLawyerFolder(Lawyer lawyerFolder) {
        this.lawyerFolder = lawyerFolder;
    }

    public Court getCourtFolder() {
        return courtFolder;
    }

    public void setCourtFolder(Court courtFolder) {
        this.courtFolder = courtFolder;
    }
}
