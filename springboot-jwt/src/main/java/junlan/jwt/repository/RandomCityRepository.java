package junlan.jwt.repository;

import junlan.jwt.domain.RandomCity;
import org.springframework.data.repository.CrudRepository;

public interface RandomCityRepository extends CrudRepository<RandomCity, Long> {
}
