package com.udea.biosegura.persistence.repository;

import com.udea.biosegura.domain.dto.PlaceDTO;
import com.udea.biosegura.domain.repository.PlaceRepository;

import java.util.List;
import java.util.Optional;

public class PlacePersistenceRepository implements PlaceRepository {
    @Override
    public List<PlaceDTO> getAll() {
        return null;
    }

    @Override
    public Optional<PlaceDTO> getPlace(String idPlace) {
        return Optional.empty();
    }

    @Override
    public PlaceDTO save(PlaceDTO place) {
        return null;
    }

    @Override
    public void delete(String idPlace) {

    }
}
