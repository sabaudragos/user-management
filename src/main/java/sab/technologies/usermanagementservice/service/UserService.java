package sab.technologies.usermanagementservice.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sab.technologies.usermanagementservice.domain.UserEntity;
import sab.technologies.usermanagementservice.dto.User;
import sab.technologies.usermanagementservice.repo.ProjectRepo;
import sab.technologies.usermanagementservice.repo.UserRepo;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private Mapper mapper;
    private UserRepo userRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ProjectRepo projectRepo;

    @Autowired
    public UserService(Mapper mapper, UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, ProjectRepo projectRepo) {
        this.mapper = mapper;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.projectRepo = projectRepo;
    }

    public User createUser(User user) {
        UserEntity userEntity = mapper.map(user, UserEntity.class);
        String password = userEntity.getPassword();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        userEntity.setPassword(encryptedPassword);
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return mapper.map(savedUserEntity, User.class);
    }

    public User getUser(Long id) {
        Optional<UserEntity> userEntityOptional = userRepo.findById(id);
        UserEntity userEntity = userEntityOptional.orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found"));
        return mapper.map(userEntity, User.class);
    }

    public User updateUser(User user) {
        UserEntity userEntity = mapper.map(user, UserEntity.class);
        if (!userEntity.getPassword().isEmpty()) {
            String newPassword = userEntity.getPassword();
            String newEncryptedPassword = bCryptPasswordEncoder.encode(newPassword);
            userEntity.setPassword(newEncryptedPassword);
        }
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return mapper.map(savedUserEntity, User.class);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public List<User> getAllByClientId(Long clientId) {
        List<UserEntity> userEntityList = userRepo.findAllByClientId(clientId);
        return mapUserEntityToDto(userEntityList);
    }

    public List<User> getAllByClientIdAndProjectIdList(Long clientId, List<Long> projectIdList) {
        List<UserEntity> userEntityList = userRepo.findAllByClientIdAndProjectIdListIn(clientId, projectIdList);
        return mapUserEntityToDto(userEntityList);
    }

    private List<User> mapUserEntityToDto(List<UserEntity> userEntityList) {
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            userList.add(mapper.map(userEntity, User.class));
        }
        return userList;
    }

}
