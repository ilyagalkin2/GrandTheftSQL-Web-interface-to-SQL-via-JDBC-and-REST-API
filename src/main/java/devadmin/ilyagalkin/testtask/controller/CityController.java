package devadmin.ilyagalkin.testtask.controller;

import devadmin.ilyagalkin.testtask.model.City;
import devadmin.ilyagalkin.testtask.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CityController {

	@Autowired
	CityRepository cityRepository;

	/**
	 * Gets all cities with GetMapping '/api/cities' or finds the ones containing
	 * <em>String</em> given as parameter
	 * @param param a name or part of the name of the city
	 * @return List with a class <em>City</em> as generic type
	 */
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllCities(@RequestParam(required = false) String param) {

		try {
			List<City> city = new ArrayList<>();
			if (param == null) {
				city.addAll(cityRepository.findAll());
			} else {
                city.addAll(cityRepository.findByNameContaining(param));

				//var example = new User();

				//to find User1
				//example.setName("User1");

				//example.setName(param);
				//cities.addAll(userRepository.findAll(Example.of(example)));
			}

			if (city.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(city, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets a city with GetMapping '/api/cities/{id}' where <em>id</em> given as a parameter
	 * @param id a parameter, id of the city in the database
	 * @return a city with this <em>id</em>
	 */
	@GetMapping("/cities/{id}")
	public ResponseEntity<City> getCityById(@PathVariable("id") int id) {
		Optional<City> cityData = cityRepository.findById(id);

		return cityData.map(city -> new ResponseEntity<>(city, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Creates a city with PostMapping '/api/cities'
	 * @param city a JSON that represents a new row of the table
	 * @return a JSON that represents the newly created row of the table
	 */
	@PostMapping("/cities")
	public ResponseEntity<City> createUser(@RequestBody City city) {
		try {
			City _city = cityRepository.save(city);
			return new ResponseEntity<>(_city, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Updates a city with PutMapping '/api/cities/{id}' where <em>id</em> is given
	 * as a parameter
	 * @param id a parameter, <em>id</em> of the city in the database
	 * @param city a JSON that represents the row (of the table) with the
	 * given <em>id</em> to which the query is made
	 * @return a JSON that represents the updated row of the table
	 */
	@PutMapping("/cities/{id}")
	public ResponseEntity<City> updateCity(@PathVariable("id") int id, @RequestBody City city) {
		Optional<City> cityData = cityRepository.findById(id);

		if (cityData.isPresent()) {
			City _city = cityData.get();
			_city.setName(city.getName());
			_city.setCountryCode(city.getCountryCode());
			_city.setDistrict(city.getDistrict());
			_city.setPopulation(city.getPopulation());
			return new ResponseEntity<>(cityRepository.save(_city), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Deletes a city with DeleteMapping '/api/cities/{id}' where <em>id</em> given
	 * as a parameter
	 * @param id a parameter, <em>id</em> of the city in the database
	 * @return nothing, empty JSON for the given <em>id</em>
	 */
	@DeleteMapping("/cities/{id}")
	public ResponseEntity<HttpStatus> deleteCity(@PathVariable("id") int id) {
		try {
			cityRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Deletes all cities with DeleteMapping '/api/cities'
	 *
	 * @return nothing, empty JSON, for whole table
	 */
	@DeleteMapping("/cities")
	public ResponseEntity<HttpStatus> deleteAllCities() {
		try {
			cityRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
