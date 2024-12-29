package com.enicar.soc.services;

import com.enicar.soc.entities.Client;
import com.enicar.soc.entities.Court;
import com.enicar.soc.entities.Folder;
import com.enicar.soc.entities.Lawyer;
import com.enicar.soc.exceptions.NotFoundException;
import com.enicar.soc.exceptions.ResourceNotFoundException;
import com.enicar.soc.repositories.ClientRepository;
import com.enicar.soc.repositories.CourtRepository;
import com.enicar.soc.repositories.FolderRepository;
import com.enicar.soc.repositories.LawyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService implements IFolderService{
    @Autowired
    FolderRepository folderRepository;

    ClientRepository clientRepository;

    CourtRepository courtRepository;

    LawyerRepository lawyerRepository;



    @Override
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    @Override
    public Folder deleteFolder(Long id) {
        Folder f = folderRepository.getReferenceById(id);
        if(f == null){
            throw new NotFoundException("this folder not found !");
        }
        folderRepository.deleteById(id);
        return f;
    }

    @Override
    public Folder getFolderById(Long id) {
        return folderRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("this folder not found !"));
    }

    @Override
    public Folder getFolderByName(String name) {
        Folder f = folderRepository.getFolderByName(name);
        if(f == null){
            throw new NotFoundException("this folder not found");
        }
        return f;
    }

    @Override
    public Folder addFolder(Folder folder) {
        // Valider et associer le client
       if (folder.getClientFolder() != null && folder.getClientFolder().getClientId() != null) {
            Client client = clientRepository.findById(folder.getClientFolder().getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            folder.setClientFolder(client);
        }

        // Valider et associer l'avocat
        if (folder.getLawyerFolder() != null && folder.getLawyerFolder().getLawyerId() != null) {
            Lawyer lawyer = lawyerRepository.findById(folder.getLawyerFolder().getLawyerId())
                    .orElseThrow(() -> new RuntimeException("Lawyer not found"));

            // Vérification si l'email est présent
            if (lawyer.getEmail() == null || lawyer.getEmail().isEmpty()) {
                throw new ResourceNotFoundException("Lawyer email cannot be null or empty");
            }
            folder.setLawyerFolder(lawyer);
        }

        // Valider et associer le tribunal
        if (folder.getCourtFolder() != null && folder.getCourtFolder().getCourtId() != null) {
            Court court = courtRepository.findById(folder.getCourtFolder().getCourtId())
                    .orElseThrow(() -> new RuntimeException("Court not found"));
            folder.setCourtFolder(court);
        }

        // Sauvegarder le dossier
        return folderRepository.save(folder);
    }
}
