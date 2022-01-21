package com.udea.biosegura.persistence.crud;

import com.udea.biosegura.persistence.entity.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceCrudRepository extends CrudRepository<Place, String> {
}
