package mathijs.bos.garage_app.action;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActionMapper implements BaseMapper<Action, ActionDTO> {

    private final ActionRepository actionRepository;

    public ActionMapper(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    @Override
    public Action toEntity(ActionDTO dto) {
        return actionRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("No action with id:" + dto.getId() + " was found."));
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
