package com.enicar.soc.services;

import com.enicar.soc.entities.Lawyer;
import com.enicar.soc.exceptions.AlreadyExistsException;
import com.enicar.soc.exceptions.NotFoundException;
import com.enicar.soc.repositories.LawyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawyerServiceImp implements ILawyerService {
    @Autowired
    LawyerRepository lawyerRepository;

    @Override
    public Lawyer getLAwyerById(Long id){
        return lawyerRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("this folder not found !")
        );
    }

    @Override
    public Lawyer getLawyerByName(String fname, String lname){
        Lawyer lawyer = lawyerRepository.getLawyerByName(fname, lname);
        if(lawyer == null){
            throw new NotFoundException("lawyer not found with this name");
        }
        return lawyer;
    }
    @Override
    public List<Lawyer> getAllLawyers(){
        return lawyerRepository.findAll();
    }
    @Override
    public Lawyer addLawyer(Lawyer lawyer) {
        // Vérifie si un avocat avec cet email existe déjà dans la base de données
        Lawyer existingLawyer = lawyerRepository.getLawyerByEmail(lawyer.getEmail());

        if (existingLawyer != null) {
            // crée et lance une exception si l'avocat existe déjà
            throw new AlreadyExistsException("A lawyer with this email already exists.");
        }

        return lawyerRepository.save(lawyer);
    }


    @Override
    public Lawyer deleteLawyer(Long id) {
        Lawyer lawyer = lawyerRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("the lawyer that you want to delete are not found !")
        );
        System.out.println("service");

        lawyerRepository.deleteById(id);
        return lawyer;
    }
}
