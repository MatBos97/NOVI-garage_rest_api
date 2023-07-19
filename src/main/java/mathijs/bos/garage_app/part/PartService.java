package mathijs.bos.garage_app.part;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartService extends BaseService<Part, PartDTO, Long> {

    @Autowired
    public PartService(PartRepository repository, PartMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Part create(PartDTO dto) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Part update(Long aLong, PartDTO dto) throws EntityNotFoundException {
        return null;
    }
}
