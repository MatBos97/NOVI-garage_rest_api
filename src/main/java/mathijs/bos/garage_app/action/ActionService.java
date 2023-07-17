package mathijs.bos.garage_app.action;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseMapper;
import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ActionService extends BaseService<Action, ActionDTO, Long> {

    @Autowired
    public ActionService(BaseRepository<Action, Long> repository, BaseMapper<Action, ActionDTO> mapper) {
        super(repository, mapper);
    }

    @Override
    public Action create(ActionDTO dto) throws EntityNotFoundException {
        Action action = mapper.toEntity(dto);
        return repository.save(action);
    }

    @Override
    public Action update(Long aLong, ActionDTO dto) throws EntityNotFoundException {
        return null;
    }
}
