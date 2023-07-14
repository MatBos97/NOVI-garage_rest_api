package mathijs.bos.garage_app.action;

import mathijs.bos.garage_app.base_classes.BaseMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ActionMapper implements BaseMapper<ActionDTO, Action> {
    @Override
    public Action toEntity(ActionDTO dto) {
        if(dto == null){
            return null;
        }

        Action action = Action.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
        action.setId(dto.getId());

        return action;
    }

    @Override
    public ActionDTO toDto(Action entity) {
        if(entity == null){
            return null;
        }

        ActionDTO actionDTO = ActionDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
        actionDTO.setId(entity.getId());

        return actionDTO;
    }

    @Override
    public List<Action> toEntity(List<ActionDTO> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ActionDTO> toDto(List<Action> entityList) {
        return entityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
