package devadmin.ilyagalkin.testtask.repository;

import devadmin.ilyagalkin.testtask.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findByNameContaining(String name);

//    @Query("select u from User u where u.name like concat('%', ?1, '%')")
//    List<User> findByNameContaining(String name);


//    Optional<User> findByNameLikeIgnoreCase(String name);

    //with named
//    @Query("select u from User u where upper(u.name) like upper(:name)")
//    Optional<City> findByNameLikeIgnoreCase_same(@Param("name") String name);

    //same but extracted via JPA Onspector panel
//    @Query("select u from User u where upper(u.name) like upper(?1)")
//    Optional<User> findByNameLikeIgnoreCase_anyNameCanBeGiven(String name);

}
