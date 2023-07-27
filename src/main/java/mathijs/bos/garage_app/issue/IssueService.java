package mathijs.bos.garage_app.issue;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import mathijs.bos.garage_app.service_record.ServiceRecord;
import mathijs.bos.garage_app.service_record.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService extends BaseService<Issue, IssueDTO, Long> {

    private final IssueRepository issueRepository;
    private final IssueMapper issueMapper;
    private final ServiceRecordRepository serviceRecordRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, IssueMapper issueMapper, ServiceRecordRepository serviceRecordRepository) {
        super(issueRepository);
        this.issueRepository = issueRepository;
        this.issueMapper = issueMapper;
        this.serviceRecordRepository = serviceRecordRepository;
    }

    @Override
    public IssueDTO create(IssueDTO dto) throws EntityNotFoundException {
        dto.setId(null);
        Issue issue = issueMapper.toEntity(dto);
        ServiceRecord serviceRecord = serviceRecordRepository.findById(dto.getServiceRecordId()).orElseThrow(EntityNotFoundException::new);
        issue.setServiceRecord(serviceRecord);

        Issue saved = issueRepository.save(issue);
        return issueMapper.toDto(saved);
    }

    @Override
    public IssueDTO update(Long id, IssueDTO dto) throws EntityNotFoundException {

        ServiceRecord serviceRecord = serviceRecordRepository.findById(dto.getServiceRecordId()).orElseThrow(EntityNotFoundException::new);

        Issue updated = issueRepository.findById(id).map(
                issue -> {
                    issue.setId(dto.getId());
                    issue.setFixAgreement(dto.getFixAgreement());
                    issue.setDescription(dto.getDescription());
                    issue.setServiceRecord(serviceRecord);

                    return issueRepository.save(issue);
                }
        ).orElseThrow(EntityNotFoundException::new);

        return issueMapper.toDto(updated);
    }

    public Issue agreeToFix(Long id) {
        return issueRepository.findById(id)
                .map(issue -> {
                    issue.setFixAgreement(true);
                    return issueRepository.save(issue);
                }).orElseThrow(() -> new IssueNotFoundException(id));
    }
}
