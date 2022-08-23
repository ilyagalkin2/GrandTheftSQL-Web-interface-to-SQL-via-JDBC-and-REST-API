package devadmin.ilyagalkin.testtask.model;

import javax.persistence.*;

/**
 * This class describes the database we create initially.
 * @see devadmin.ilyagalkin.testtask.controller.ApiForDatabase
 * @see devadmin.ilyagalkin.testtask.controller.CityController
 *
 */
@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "countryCode", nullable = false, length = 30)
    private String countryCode;

    @Column(name = "district", nullable = false, length = 30)
    private String district;
    @Column(name = "population", nullable = false, length = 30)
    private String population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}