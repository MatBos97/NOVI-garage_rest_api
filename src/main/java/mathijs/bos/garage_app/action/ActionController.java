package mathijs.bos.garage_app.action;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/action")
public class ActionController extends BaseController<Action> {

    private final ActionService actionService;

    @Autowired
    public ActionController(ActionService actionService) {
        super(actionService);
        this.actionService = actionService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> UpdateAction(@PathVariable Long id, @RequestBody Action newAction){
        Optional<Action> actionOptional = actionService.update(id, newAction);

        if(actionOptional.isEmpty()){
            return new ResponseEntity<>("Action not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(actionOptional.get(), HttpStatus.OK);
    }
}
