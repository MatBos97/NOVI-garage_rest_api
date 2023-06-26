package mathijs.bos.garage_app.base_classes;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {

    private BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(Long id) {
        Optional<T> optionalEntity = repository.findById(id);
        if(optionalEntity.isPresent()) {
            return optionalEntity.get();
        }
        else {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        Optional<T> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            repository.delete(optionalEntity.get());
        } else {
            throw new EntityNotFoundException("Entity not found with id: " + id);
        }
    }
}
