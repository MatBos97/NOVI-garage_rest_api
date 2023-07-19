package mathijs.bos.garage_app.custom_action;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomActionService extends BaseService<CustomAction, CustomActionDTO, Long> {

    @Autowired
    public CustomActionService(CustomActionRepository repository, CustomActionMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public CustomAction create(CustomActionDTO dto) throws EntityNotFoundException {
        return null;
    }

    @Override
    public CustomAction update(Long aLong, CustomActionDTO dto) throws EntityNotFoundException {
        return null;
    }
}
