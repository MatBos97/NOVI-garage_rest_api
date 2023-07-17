package mathijs.bos.garage_app.base_classes;

import java.util.List;

public interface BaseMapper <E, D> {

    E toEntity(D dto);
    D toDto(E entity);


    default List<E> toEntity(List<D> dtoList){
        return dtoList.stream().map(this::toEntity).toList();
    }

    default List<D> toDto(List<E> entityList){
        return entityList.stream().map(this::toDto).toList();
    }

}
