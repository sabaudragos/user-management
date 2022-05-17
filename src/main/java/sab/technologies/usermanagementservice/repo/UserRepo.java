package sab.technologies.usermanagementservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sab.technologies.usermanagementservice.domain.ProjectEntity;
import sab.technologies.usermanagementservice.domain.UserEntity;
import sab.technologies.usermanagementservice.dto.User;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByClientId(Long clientId);

//    @Query(value = "select userentity0_.id as id1_2_, userentity0_.client_id as client_i2_2_, userentity0_.email as email3_2_, userentity0_.first_name as first_na4_2_, userentity0_.last_name as last_nam5_2_, userentity0_.password as password6_2_ from users userentity0_  left outer join user_project projectidl1_ on userentity0_.id=projectidl1_.project_id left outer join project projectent2_  on projectidl1_.user_id=projectent2_.id where userentity0_.client_id=:clientId and (projectent2_.id in ( :projectIdList))")
    List<UserEntity> findAllByClientIdAndProjectIdListIn(Long clientId, List<Long> projectIdList);

}
