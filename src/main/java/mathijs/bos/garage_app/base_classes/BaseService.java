package mathijs.bos.garage_app.base_classes;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;


public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>> {

    protected final R repository;

    public BaseService(R repository){
        this.repository = repository;
    }

    public List<T> findAll(){
        return repository.findAll();
    }

    public Optional<T> findById(ID id){
        return repository.findById(id);
    }

    @Transactional
    public T create(T entity){
        return repository.save(entity);
    }

    @Transactional
    public T update(ID id, T newEntity) throws EntityNotFoundException, IllegalAccessException {
        T entity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Did not find entity with id: " + id));


        Class<?> c = entity.getClass();

        for(Field field : c.getDeclaredFields()){
            field.setAccessible(true);
            Object newValue = field.get(newEntity);

            if(newValue != null){
                field.set(entity, newValue);
            }
        }

        return repository.save(entity);
    }

    @Transactional
    public void delete(ID id){
        Optional<T> entity = repository.findById(id);
        if (entity.isEmpty()){
            throw new EntityNotFoundException("Did not find entity with id: " + id);
        }

        repository.delete(entity.get());
    }
}
