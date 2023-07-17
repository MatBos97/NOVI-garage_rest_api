package mathijs.bos.garage_app.action;

import mathijs.bos.garage_app.base_classes.BaseMapper;

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
}
