package mathijs.bos.garage_app.base_classes;

import java.util.List;

public interface BaseMapper <E, D> {

    public E toEntity(D dto);
    public D toDto(E entity);

    public List<E> toEntity(List<D> dtoList);
    public List<D> toDto(List<E> entityList);

}
