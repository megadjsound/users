package skillbox.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skillbox.com.users.entity.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer> {
}
