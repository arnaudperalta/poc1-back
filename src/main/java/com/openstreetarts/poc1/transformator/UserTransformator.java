package com.openstreetarts.poc1.transformator;

import com.openstreetarts.poc1.dto.UserDTO;
import com.openstreetarts.poc1.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Component
@Slf4j
@Transactional
public class UserTransformator implements AbstractTransformator<UserEntity, UserDTO> {

    private static ModelMapper modelMapper;

    private static ModelMapper modelMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(PRIVATE);
        }
        return modelMapper;
    }


    @Override
    public UserDTO modelToDto(final UserEntity model) {
        if (null == model) {
            return null;
        }
        return modelMapper().map(model, UserDTO.class);
    }

    @Override
    public UserEntity dtoToModel(final UserDTO dto) {
        if (null == dto) {
            return null;
        }
        return modelMapper().map(dto, UserEntity.class);
    }

    @Override
    public List<UserDTO> modelsToDtos(final List<UserEntity> models) {
        return models.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    @Override
    public List<UserEntity> dtosToModels(final List<UserDTO> dtos) {
        return dtos.stream().map(this::dtoToModel).collect(Collectors.toList());
    }
}
