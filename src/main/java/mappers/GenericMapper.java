package mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper<Entity, Dto> {

    private final ModelMapper modelMapper;

    public GenericMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Dto entityToDto(Entity entity, Class<Dto> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public Entity dtoToEntity(Dto dto, Class<Entity> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}