package mathijs.bos.garage_app.action;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActionService extends BaseService<Action, ActionDTO, Long> {

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;

    @Autowired
    public ActionService(ActionRepository actionRepository, ActionMapper actionMapper) {
        super(actionRepository, actionMapper);
        this.actionRepository = actionRepository;
        this.actionMapper = actionMapper;
    }

    @Override
    public ActionDTO create(ActionDTO dto) throws EntityNotFoundException {
        dto.setId(null);
        Action action = actionMapper.toEntity(dto);

        Action saved = actionRepository.save(action);
        return actionMapper.toDto(saved);
    }

    @Override
    public ActionDTO update(Long id, ActionDTO dto) throws EntityNotFoundException {
        Action updated = actionRepository.findById(id).map(
                action -> {
                    action.setId(dto.getId());
                    action.setName(dto.getName());
                    action.setPrice(dto.getPrice());

                    return actionRepository.save(action);
                }
        ).orElseThrow(EntityNotFoundException::new);

        return actionMapper.toDto(updated);
    }
}
