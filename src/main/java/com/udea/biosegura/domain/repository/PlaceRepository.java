package com.udea.biosegura.domain.repository;

import com.udea.biosegura.domain.dto.PlaceDTO;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository {

    List<PlaceDTO> getAll();
    Optional<PlaceDTO> getPlace(String idPlace);
    PlaceDTO save(PlaceDTO place);
    String delete(String idPlace);


}
