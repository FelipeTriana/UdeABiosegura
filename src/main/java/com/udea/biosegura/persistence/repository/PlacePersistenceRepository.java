package com.udea.biosegura.persistence.repository;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.persistence.crud.PlaceCrudRepository;
import com.udea.biosegura.persistence.entity.Place;
import com.udea.biosegura.persistence.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.udea.biosegura.domain.repository.PlaceRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlacePersistenceRepository implements PlaceRepository {
    @Autowired
    private PlaceCrudRepository placeCrudRepository;

    @Autowired
    private PlaceMapper mapper;

    @Override
    public List<PlaceDTO> getAll() {
        List<Place> places = (List<Place>) placeCrudRepository.findAll(); //Ready
        return mapper.toPlacesDTO(places);
    }

    @Override
    public Optional<PlaceDTO> getPlace(String idPlace) {
        Optional<Place> place = placeCrudRepository.findById(idPlace);  //Ready
        return  place.map(plc ->mapper.toPlaceDTO(plc));
    }

    @Override
    public PlaceDTO save(PlaceDTO placedto) {
        Place place = mapper.toPlace(placedto);
        return mapper.toPlaceDTO(placeCrudRepository.save(place));      //Ready
    }

    @Override
    public String delete(String idPlace) {
        placeCrudRepository.deleteById(idPlace);                       //Ready
        return "Success";
    }
}
