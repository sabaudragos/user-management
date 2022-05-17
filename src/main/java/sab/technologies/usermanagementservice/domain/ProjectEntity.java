package sab.technologies.usermanagementservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
@ToString
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    @ManyToMany(mappedBy = "projectIdList")
    private List<UserEntity> userEntityList;

}
