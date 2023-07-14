package mathijs.bos.garage_app.issue;

import jakarta.persistence.EntityNotFoundException;

public class IssueNotFoundException extends EntityNotFoundException {

    public IssueNotFoundException(Long id) {
        super("Could not find issue with id:" + id);
    }
}
