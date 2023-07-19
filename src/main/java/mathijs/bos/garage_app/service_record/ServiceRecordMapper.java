package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.action.Action;
import mathijs.bos.garage_app.base_classes.BaseMapper;
import mathijs.bos.garage_app.custom_action.CustomAction;
import mathijs.bos.garage_app.issue.Issue;
import mathijs.bos.garage_app.part.Part;
import org.springframework.stereotype.Component;

@Component
public class ServiceRecordMapper implements BaseMapper<ServiceRecord, ServiceRecordDTO> {
    @Override
    public ServiceRecord toEntity(ServiceRecordDTO dto) {
        return new ServiceRecord(
                dto.getInspection(),
                dto.getRepair(),
                dto.getStatus(),
                dto.getReceipt(),
                dto.getTotalCost()
        );
    }

    @Override
    public ServiceRecordDTO toDto(ServiceRecord entity) {
        return new ServiceRecordDTO(
                entity.getInspection(),
                entity.getRepair(),
                entity.getStatus(),
                entity.getReceipt(),
                entity.getTotalCost(),
                entity.getCar().getId(),
                entity.getParts().stream().map(Part::getId).toList(),
                entity.getActions().stream().map(Action::getId).toList(),
                entity.getCustomActions().stream().map(CustomAction::getId).toList(),
                entity.getIssues().stream().map(Issue::getId).toList()
        );
    }
}
