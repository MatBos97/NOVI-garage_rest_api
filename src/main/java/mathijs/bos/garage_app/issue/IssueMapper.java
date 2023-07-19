package mathijs.bos.garage_app.issue;

import mathijs.bos.garage_app.base_classes.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class IssueMapper implements BaseMapper<Issue, IssueDTO> {
    @Override
    public Issue toEntity(IssueDTO dto) {
        return new Issue(
                dto.getId(),
                dto.getDescription(),
                dto.getFixAgreement()
        );
    }

    @Override
    public IssueDTO toDto(Issue entity) {
        return new IssueDTO(
                entity.getDescription(),
                entity.getFixAgreement(),
                entity.getServiceRecord().getId()
        );
    }
}
