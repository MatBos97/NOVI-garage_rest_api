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
    public ResponseEntity<List<D>> findAll(){
        List<D> all = service.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable ID id){
        D byId = service.findById(id);
        return ResponseEntity.ok(byId);
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D dto){
        try {
            D d = service.create(dto);
            return ResponseEntity.ok(d);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable ID id, @RequestBody D dto){
        try {
            D updated = service.update(id, dto);
            return ResponseEntity.ok(updated);
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
