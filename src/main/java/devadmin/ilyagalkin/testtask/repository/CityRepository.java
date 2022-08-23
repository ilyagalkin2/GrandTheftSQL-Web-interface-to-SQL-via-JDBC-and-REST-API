package devadmin.ilyagalkin.testtask.repository;

import devadmin.ilyagalkin.testtask.model.City;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

/**
 * An Interface in which various types of standardized for <em>Spring</em>'s
 * <em>Hibernate</em> from <em>Java Persistence API</em> (JPA) queries
 * can be described
 */
public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByNameContaining(String name);

}
