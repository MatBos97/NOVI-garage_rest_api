package mathijs.bos.garage_app.action;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActionService extends BaseService<Action> {

    private ActionRepository actionRepository;

    @Autowired
    public ActionService(ActionRepository actionRepository) {
        super(actionRepository);
        this.actionRepository = actionRepository;
    }

    public Optional<Action> update(Long id, Action newAction) {
        return actionRepository.findById(id)
                .map(action -> {
                    action.setId(newAction.getId());
                    action.setName(newAction.getName());
                    action.setPrice(newAction.getPrice());

                    actionRepository.save(action);
                    return action;
                });
    }
}
