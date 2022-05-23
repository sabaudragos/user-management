package sab.technologies.usermanagementservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sab.technologies.usermanagementservice.domain.UserEntity;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByClientId(Long clientId);
}
