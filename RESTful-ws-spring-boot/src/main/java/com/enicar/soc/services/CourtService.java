package com.enicar.soc.services;

import com.enicar.soc.entities.Court;
import com.enicar.soc.exceptions.NotFoundException;
import com.enicar.soc.repositories.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtService implements ICourtService{
    @Autowired
    CourtRepository courtRepository;

    @Override
    public Court getCourtByName(String name) {
        Court court = courtRepository.getCourtByName(name);
        if(court == null){
            throw new NotFoundException("Folder not found !!");
        }
        return court;
    }

    @Override
    public Court getCourtById(Long id){
        Court court = courtRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Court not found !!")
        );
        return court;
    }

    @Override
    public Court addCourt(Court court) {
        return courtRepository.save(court);
    }


    @Override
    public List<Court> getAllCourts(){
        return courtRepository.findAll();
    }

    @Override
    public Court deleteCourt(Long  id){
        Court court = courtRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("court not fount !!")
        );
        courtRepository.deleteById(id);
        return court;

    }
}
