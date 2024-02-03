package skillbox.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skillbox.com.users.entity.CityEntity;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
}
