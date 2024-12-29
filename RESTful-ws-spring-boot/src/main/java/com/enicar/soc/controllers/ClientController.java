package com.enicar.soc.controllers;

import com.enicar.soc.entities.Client;
import com.enicar.soc.entities.Lawyer;
import com.enicar.soc.repositories.CourtRepository;
import com.enicar.soc.repositories.FolderRepository;
import com.enicar.soc.repositories.LawyerRepository;
import com.enicar.soc.services.ClientService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    FolderRepository folderRepository;
    @Autowired
    LawyerRepository lawyerRepository;
    @Autowired
    CourtRepository courtRepository;


    @GetMapping("/search")
    public ResponseEntity<Client> getClientByName(@RequestParam("fname") String fname, @RequestParam("lname") String lname){
        Client client = clientService.getClientByName(fname, lname);
        return ResponseEntity.ok(client);
    }
    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client client1 =clientService.addClient(client);
        return ResponseEntity.ok(client1);
    }

}
