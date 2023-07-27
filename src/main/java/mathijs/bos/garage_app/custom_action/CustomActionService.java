package mathijs.bos.garage_app.custom_action;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.base_classes.BaseService;
import mathijs.bos.garage_app.service_record.ServiceRecord;
import mathijs.bos.garage_app.service_record.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomActionService extends BaseService<CustomAction, CustomActionDTO, Long> {

    private final CustomActionRepository customActionRepository;
    private final CustomActionMapper customActionMapper;
    private final ServiceRecordRepository serviceRecordRepository;

    @Autowired
    public CustomActionService(CustomActionRepository customActionRepository, CustomActionMapper customActionMapper, ServiceRecordRepository serviceRecordRepository) {
        super(customActionRepository);
        this.customActionRepository = customActionRepository;
        this.customActionMapper = customActionMapper;
        this.serviceRecordRepository = serviceRecordRepository;
    }

    @Override
    public CustomActionDTO create(CustomActionDTO dto) throws EntityNotFoundException {
        dto.setId(null);
        CustomAction customAction = customActionMapper.toEntity(dto);
        ServiceRecord serviceRecord = serviceRecordRepository.findById(dto.getServiceRecordId()).orElseThrow(EntityNotFoundException::new);
        customAction.setServiceRecord(serviceRecord);

        CustomAction saved = customActionRepository.save(customAction);
        return customActionMapper.toDto(saved);
    }

    @Override
    public CustomActionDTO update(Long id, CustomActionDTO dto) throws EntityNotFoundException {

        ServiceRecord serviceRecord = serviceRecordRepository.findById(dto.getServiceRecordId()).orElseThrow(EntityNotFoundException::new);

        CustomAction updated = customActionRepository.findById(id).map(
                customAction -> {
                    customAction.setId(dto.getId());
                    customAction.setDescription(dto.getDescription());
                    customAction.setPrice(dto.getPrice());
                    customAction.setServiceRecord(serviceRecord);

                    return customActionRepository.save(customAction);
                }
        ).orElseThrow(EntityNotFoundException::new);

        return customActionMapper.toDto(updated);
    }
}
