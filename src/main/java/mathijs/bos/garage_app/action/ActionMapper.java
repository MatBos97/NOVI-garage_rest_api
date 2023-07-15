package mathijs.bos.garage_app.action;

import mathijs.bos.garage_app.base_classes.BaseMapper;

import java.util.List;

public class ActionMapper implements BaseMapper<Action, ActionDTO> {


    @Override
    public Action toEntity(ActionDTO dto) {
        return new Action(dto.getName(), dto.getPrice());
    }

    @Override
    public ActionDTO toDto(Action entity) {
        return new ActionDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice()
        );
    }

    @Override
    public List<Action> toEntity(List<ActionDTO> dtoList) {
        return dtoList.stream()
                .map(this::toEntity).toList();
    }

    @Override
    public List<ActionDTO> toDto(List<Action> entityList) {
        return entityList.stream()
                .map(this::toDto).toList();
    }
}
