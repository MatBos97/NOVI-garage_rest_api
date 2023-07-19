package mathijs.bos.garage_app.custom_action;

import mathijs.bos.garage_app.base_classes.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomActionMapper implements BaseMapper<CustomAction, CustomActionDTO> {
    @Override
    public CustomAction toEntity(CustomActionDTO dto) {
        return new CustomAction(
                dto.getId(),
                dto.getDescription(),
                dto.getPrice()
        );
    }

    @Override
    public CustomActionDTO toDto(CustomAction entity) {
        return new CustomActionDTO(
                entity.getDescription(),
                entity.getPrice(),
                entity.getServiceRecord().getId()
        );
    }
}
