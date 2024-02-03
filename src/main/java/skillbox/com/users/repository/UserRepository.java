package skillbox.com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import skillbox.com.users.entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByDeletedFalse();
    Boolean existsByIdAndDeletedFalse(Integer id);
    Boolean existsByLoginAndDeletedFalse(String login);
    Optional<UserEntity> findByIdAndDeletedFalse(Integer id);
}
