package repository;

import entity.Employee;
import entity.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfficeRepository extends CrudRepository<Office, String> {
    Office findByOfficeCode(String officeCode);
    List<Office> findByCountryOrderByCityAsc(String country);

}