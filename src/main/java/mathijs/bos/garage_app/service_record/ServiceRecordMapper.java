package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.base_classes.BaseMapper;


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
                entity.getTotalCost()
        );
    }
}
