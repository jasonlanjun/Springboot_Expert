package junlan.jwt.service;

import junlan.jwt.domain.RandomCity;
import junlan.jwt.domain.User;

import java.util.List;

public interface GenericService {
    User findByUsername(String username);
    List<User> findAllUsers();
    List<RandomCity> findAllRandomCities();
}
