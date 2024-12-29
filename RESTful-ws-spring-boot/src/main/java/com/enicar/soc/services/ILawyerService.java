package com.enicar.soc.services;

import com.enicar.soc.entities.Lawyer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILawyerService {
    //Lawyer getLAwyerById(Long id);

    Lawyer getLawyerByName(String fname, String lname);

    Lawyer addLawyer(Lawyer avocat);

    List<Lawyer> getAllLawyers();

    Lawyer getLAwyerById(Long id);

    Lawyer deleteLawyer(Long id);
}
