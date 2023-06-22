package mathijs.bos.garage_app.base_classes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {

    private BaseRepository<T> repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(Long id) {
        Optional<T> optionalEntity = repository.findById(id);
        return optionalEntity.orElse(null);
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public boolean delete(Long id) {
        Optional<T> optionalEntity = repository.findById(id);
        if (optionalEntity.isPresent()) {
            repository.delete(optionalEntity.get());
            return true;
        } else {
            return false;
        }
    }
}
