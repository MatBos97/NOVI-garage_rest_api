package mathijs.bos.garage_app.base_classes;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class BaseService<E, D extends BaseDTO, ID extends Serializable> {

    protected final BaseRepository<E, ID> repository;
    protected final BaseMapper<E, D> mapper;

    public BaseService(BaseRepository<E, ID> repository, BaseMapper<E, D> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<E> findAll(){
        return repository.findAll();
    }

    public Optional<E> findById(ID id){
        return repository.findById(id);
    }

    @Transactional
    public abstract E create(D dto) throws EntityNotFoundException;

    @Transactional
    public abstract E update(ID id, D dto) throws EntityNotFoundException;

    @Transactional
    public void delete(ID id){
        Optional<E> entity = repository.findById(id);
        if (entity.isEmpty()){
            throw new EntityNotFoundException("Did not find entity with id: " + id);
        }

        repository.delete(entity.get());
    }
}
