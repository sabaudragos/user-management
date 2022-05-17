package sab.technologies.usermanagementservice.util;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    @Bean
    private Mapper mapper() {
        return new DozerBeanMapper();
    }
}
