package mathijs.bos.garage_app.issue;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import mathijs.bos.garage_app.service_record.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IssueService extends BaseService<Issue, Long, IssueRepository> {

    private final IssueRepository repository;
    private final ServiceRecordRepository serviceRecordRepository;

    @Autowired
    public IssueService(IssueRepository repository, ServiceRecordRepository serviceRecordRepository) {
        super(repository);
        this.repository = repository;
        this.serviceRecordRepository = serviceRecordRepository;
    }

    @Override
    public Issue create(Issue entity) {
        return serviceRecordRepository.findById(entity.getServiceRecord().getId())
                .map(serviceRecord -> {
                    entity.setServiceRecord(serviceRecord);
                    return super.create(entity);
                }).orElseThrow(() -> new EntityNotFoundException("Could not find service record with id:" + entity.getServiceRecord().getId()));
    }

    public Issue agreeToFix(Long id) {
        return repository.findById(id)
                .map(issue -> {
                    issue.setFixAgreement(true);
                    return repository.save(issue);
                }).orElseThrow(() -> new IssueNotFoundException(id));
    }
}
