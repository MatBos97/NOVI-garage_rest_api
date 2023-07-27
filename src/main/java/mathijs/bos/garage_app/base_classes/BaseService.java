package mathijs.bos.garage_app.base_classes;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;


public abstract class BaseService<E, D extends BaseDTO, ID extends Serializable> {

    private final BaseRepository<E, ID> repository;
    private final BaseMapper<E, D> mapper;

    public BaseService(BaseRepository<E, ID> repository, BaseMapper<E, D> mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<D> findAll(){
        List<E> all = repository.findAll();
        return mapper.toDto(all);
    }

    public D findById(ID id) throws EntityNotFoundException {
        E e = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return mapper.toDto(e);
    }

    @Transactional
    public abstract D create(D dto) throws EntityNotFoundException;

    @Transactional
    public abstract D update(ID id, D dto) throws EntityNotFoundException;

    @Transactional
    public void delete(ID id){
        E entity = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        repository.delete(entity);
    }
}
