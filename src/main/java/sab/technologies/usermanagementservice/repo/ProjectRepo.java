package sab.technologies.usermanagementservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import sab.technologies.usermanagementservice.domain.ProjectEntity;

import java.util.List;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> findAllByIdIn(List<Long> ids);

}
