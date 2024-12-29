package com.enicar.soc.services;

import com.enicar.soc.entities.Client;
import com.enicar.soc.exceptions.AlreadyExistsException;
import com.enicar.soc.exceptions.NotFoundException;
import com.enicar.soc.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ClientService implements IClientServce{
    @Autowired
    ClientRepository clientRepository;


    @Override
    public Client getClientByName(String fname, String lname){
        Client client = clientRepository.getClientByName(fname, lname);
        if(client == null){
            throw new NotFoundException("client not found with this name");
        }
        return client;
    }
    @Override
    public List<Client> getAllClients(){
       return clientRepository.findAll();
   }
   @Override
    public Client getClientById(Long id){
       Client client = clientRepository.findById(id).orElseThrow(
               ()-> new NotFoundException("this client is not found !")
       );
       return client;
   }
   @Override
   @PostMapping
   public Client addClient(Client client){
       Client ExistingClient = clientRepository.getClientByEmail(client.getEmail());
       if(ExistingClient != null){
           throw new AlreadyExistsException("Client already exist !");
       }

       return clientRepository.save(client);
   }
   @DeleteMapping("{id}")
   @Override
    public Client deleteClientById(@PathVariable Long id){
       clientRepository.findById(id).orElseThrow(
               ()-> new NotFoundException("Client not found with this name !")
       );
       clientRepository.deleteById(id);
       return null;
   }
}
