package mathijs.bos.garage_app.issue;

import mathijs.bos.garage_app.base_classes.BaseRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService extends BaseService<Issue> {

    @Autowired
    public IssueService(IssueRepository repository) {
        super(repository);
    }
}
