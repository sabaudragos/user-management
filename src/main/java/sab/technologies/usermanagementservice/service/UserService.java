package sab.technologies.usermanagementservice.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sab.technologies.usermanagementservice.domain.UserEntity;
import sab.technologies.usermanagementservice.dto.User;
import sab.technologies.usermanagementservice.repo.UserRepo;
import sab.technologies.usermanagementservice.util.MapperUtil;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final MapperUtil mapperUtil;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(MapperUtil mapperUtil,
                       UserRepo userRepo,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.mapperUtil = mapperUtil;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(User user) {
        UserEntity userEntity = mapperUtil.map(user, UserEntity.class);
        String password = userEntity.getPassword();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        userEntity.setPassword(encryptedPassword);
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return mapperUtil.map(savedUserEntity, User.class);
    }

    public User getUser(Long id) {
        Optional<UserEntity> userEntityOptional = userRepo.findById(id);
        UserEntity userEntity = userEntityOptional.orElseThrow(() -> new EntityNotFoundException("User with id: " + id + " not found"));
        userEntity.setPassword(null);
        return mapperUtil.map(userEntity, User.class);
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepo.findAll();
        userEntityList.forEach(userEntity -> userEntity.setPassword(null));
        return mapperUtil.mapList(userEntityList, User.class);
    }

    public User updateUser(User user) {
        UserEntity userEntity = mapperUtil.map(user, UserEntity.class);

        if (!userEntity.getPassword().isEmpty()) {
            String newPassword = userEntity.getPassword();
            String newEncryptedPassword = bCryptPasswordEncoder.encode(newPassword);
            userEntity.setPassword(newEncryptedPassword);
        }

        UserEntity savedUserEntity = userRepo.save(userEntity);
        return mapperUtil.map(savedUserEntity, User.class);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public List<User> getAllByClientId(Long clientId) {
        List<UserEntity> userEntityList = userRepo.findAllByClientId(clientId);
        userEntityList.forEach(userEntity -> userEntity.setPassword(null));
        return mapperUtil.mapList(userEntityList, User.class);
    }
}
