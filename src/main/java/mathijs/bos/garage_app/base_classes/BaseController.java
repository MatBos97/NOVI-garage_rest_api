package mathijs.bos.garage_app.base_classes;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public class BaseController<E, D extends BaseDTO, ID extends Serializable> {

    protected final BaseService<E, D, ID> service;

    public BaseController(BaseService<E, D, ID> service){
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<E>> findAll(){
        List<E> all = service.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> findById(@PathVariable ID id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<E> create(@RequestBody D dto){
        try {
            E entity = service.create(dto);
            return ResponseEntity.ok(entity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable ID id, @RequestBody D dto){
        try {
            E updatedEntity = service.update(id, dto);
            return ResponseEntity.ok(updatedEntity);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
