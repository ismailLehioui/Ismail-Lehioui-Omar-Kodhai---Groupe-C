package com.enicar.soc.controllers;

import com.enicar.soc.entities.Court;
import com.enicar.soc.services.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courts")
public class CourtController {
    @Autowired
    CourtService courtService;

    @GetMapping
    public ResponseEntity<List<Court>> getAllCourts(){
        List<Court> courts = courtService.getAllCourts();
        return ResponseEntity.ok(courts);
    }
    @GetMapping("/search")
    public ResponseEntity<Court> getCourtByName(@RequestParam("name") String name){
        Court f = courtService.getCourtByName(name);
        return ResponseEntity.ok(f);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Court> getCourtById(@PathVariable("id") Long id){
        return ResponseEntity.ok(courtService.getCourtById(id));
    }
    @PostMapping
    public ResponseEntity<Court> addCourt(@RequestBody Court court){
        return ResponseEntity.ok(courtService.addCourt(court));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Court> deleteCourt(@PathVariable("id") Long id){
        return ResponseEntity.ok(courtService.deleteCourt(id));
    }
}
