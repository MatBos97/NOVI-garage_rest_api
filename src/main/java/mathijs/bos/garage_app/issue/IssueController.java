package mathijs.bos.garage_app.issue;


import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issue")
public class IssueController extends BaseController<Issue, Long, IssueService> {

    private final IssueService service;

    @Autowired
    public IssueController(IssueService service) {
        super(service);
        this.service = service;
    }

    @PatchMapping("/{id}/agreed_to_fix")
    public ResponseEntity<Issue> agreedToFix(@PathVariable Long id){
        Issue issue = service.agreeToFix(id);
        return ResponseEntity.ok(issue);
    }

}
