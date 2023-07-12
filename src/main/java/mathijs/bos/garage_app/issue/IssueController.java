package mathijs.bos.garage_app.issue;


import mathijs.bos.garage_app.base_classes.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issue")
public class IssueController extends BaseController<Issue, Long, IssueService> {

    @Autowired
    public IssueController(IssueService service) {
        super(service);
    }
}
