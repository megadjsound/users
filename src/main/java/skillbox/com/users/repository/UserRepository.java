package skillbox.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import skillbox.com.users.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
