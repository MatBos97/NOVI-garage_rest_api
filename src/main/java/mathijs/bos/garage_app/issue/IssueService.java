package mathijs.bos.garage_app.issue;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService extends BaseService<Issue, IssueDTO, Long> {

    private final IssueRepository repository;

    @Autowired
    public IssueService(IssueRepository repository, IssueMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }


    @Override
    public Issue create(IssueDTO dto) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Issue update(Long aLong, IssueDTO dto) throws EntityNotFoundException {
        return null;
    }

    public Issue agreeToFix(Long id) {
        return repository.findById(id)
                .map(issue -> {
                    issue.setFixAgreement(true);
                    return repository.save(issue);
                }).orElseThrow(() -> new IssueNotFoundException(id));
    }
}
