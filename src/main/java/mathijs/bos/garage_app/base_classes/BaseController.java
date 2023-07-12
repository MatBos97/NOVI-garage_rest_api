package mathijs.bos.garage_app.base_classes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<T, ID, S extends BaseService<T, ID, ? extends JpaRepository<T, ID>>> {

    protected final S service;

    public BaseController(S service){
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<T>> findAll(){
        List<T> all = service.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity){
        T createdEntity = service.create(entity);
        return new ResponseEntity<>(createdEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T newEntity){
        try {
            T updatedEntity = service.update(id, newEntity);
            return ResponseEntity.ok(updatedEntity);
        } catch (IllegalAccessException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
