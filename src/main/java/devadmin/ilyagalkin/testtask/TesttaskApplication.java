package devadmin.ilyagalkin.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Runs the application.
 * @author ilya galkin
 * @version 0.1
 * @see devadmin.ilyagalkin.testtask.controller.CityController
 * @see devadmin.ilyagalkin.testtask.controller.ApiForDatabase
 */
@SpringBootApplication
public class TesttaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesttaskApplication.class, args);

		System.out.println("Web interface is available at: http://localhost:8080/homepage");
	}

}

//### CRUD (Create, Read, Update, Delete)
//
//### CREATE
//POST http://localhost:8080/api/cities
//{
//    "name": "cityA",
//    "countryCode": "countryCodeA",
//    "district": "districtA",
//    "population": "populationA"
//}
//
//###
//
//POST http://localhost:8080/api/cities
//{
//    "name": "cityB",
//    "countryCode": "countryCodeB",
//    "district": "districtB",
//    "population": "populationB"
//}
//
//###
//
//POST http://localhost:8080/api/cities
//{
//    "name": "new_city_A",
//    "countryCode": "countryCodeA",
//    "district": "districtA",
//    "population": "populationA"
//}
//
//###
//
//POST http://localhost:8080/api/cities
//{
//    "name": "new_city_B",
//    "countryCode": "countryCodeB",
//    "district": "districtB",
//    "population": "population asfsadvfsdv"
//}
//
//### READ all cities
//
//GET http://localhost:8080/api/cities
//
//#
//[
//    {
//        "id": 1,
//        "name": "cityA",
//        "countryCode": "countryCodeA",
//        "district": "districtA",
//        "population": "populationA"
//    },
//    {
//        "id": 2,
//        "name": "cityB",
//        "countryCode": "countryCodeB",
//        "district": "districtB",
//        "population": "populationB"
//    },
//    {
//        "id": 3,
//        "name": "new_city_A",
//        "countryCode": "countryCodeA",
//        "district": "districtA",
//        "population": "populationA"
//    },
//    {
//        "id": 4,
//        "name": "new_city_B",
//        "countryCode": "countryCodeB",
//        "district": "districtB",
//        "population": "population asfsadvfsdv"
//    }
//]
//
//### READ city #2
//
//GET http://localhost:8080/api/cities/2
//
//#
//{
//    "id": 2,
//    "name": "cityB",
//    "countryCode": "countryCodeB",
//    "district": "districtB",
//    "population": "populationB"
//}
//
//###
//
//
//PUT http://localhost:8080/api/cities/3
//{
//    "name": "new_city_C",
//    "countryCode": "countryCodeA",
//    "district": "districtA",
//    "population": "populationA"
//}
//
//# puts id=3 after id=4, IDs are 1,2,4,3
//{
//    "id": 3,
//    "name": "new_city_C",
//    "countryCode": "countryCodeB",
//    "district": "districtB",
//    "population": "population asfsadvfsdv"
//}
//###
//
//DELETE http://localhost:8080/api/cities/2
//
//# deletes id=2, IDs are 1,4,3
//
//###
//
//DELETE http://localhost:8080/api/cities
//
//# deletes all cities, IDs are: none