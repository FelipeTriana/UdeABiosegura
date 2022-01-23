package com.udea.biosegura.domain.service;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<PlaceDTO> getAll(){return placeRepository.getAll();}

    public Optional<PlaceDTO> getPlace(String idPlace){return placeRepository.getPlace(idPlace);}

    public PlaceDTO save(PlaceDTO placedto){return placeRepository.save(placedto);}

    public boolean delete(String idPlace){
        return getPlace(idPlace).map(usr -> {
            placeRepository.delete(idPlace);
            return true;
        }).orElse(false);
    }

}
