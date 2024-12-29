package com.enicar.soc.controllers;

import com.enicar.soc.entities.Lawyer;
import com.enicar.soc.services.LawyerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/lawyers")
public class LawyerController {
    @Autowired
    LawyerServiceImp lawyerService;

    @GetMapping("/search")
    public ResponseEntity<Map<String,Lawyer>> getLawyerByName(
            @RequestParam("fname") String fname,
            @RequestParam("lname") String lname) {
        Lawyer lawyer = lawyerService.getLawyerByName(fname, lname);
        Map<String, Lawyer> map = new HashMap<>();
        map.put("avocat trouvé avec succes", lawyer);
        return ResponseEntity.ok(map);
    }

    @PostMapping
    public ResponseEntity<Lawyer> addLawyer(@RequestBody Lawyer lawyer) {
        Lawyer l = lawyerService.addLawyer(lawyer); // La logique d'exception est déjà gérée dans le service
    return ResponseEntity.ok(l);
    }


    @GetMapping
    public ResponseEntity<List<Lawyer>> getAllLawyers(){
        List<Lawyer> lawyers= lawyerService.getAllLawyers();
        return ResponseEntity.ok(lawyers);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Lawyer> getLawyerById(@PathVariable("id") Long id){
        return ResponseEntity.ok(lawyerService.getLAwyerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteLawyer(@PathVariable("id") Long id){
        System.out.println("controller");

        Lawyer lawyer= lawyerService.deleteLawyer(id);
        Map<String, Object> response= new HashMap<>();
        response.put("Lawyer deleted", lawyer);
        return ResponseEntity.ok(response);
    }
}
