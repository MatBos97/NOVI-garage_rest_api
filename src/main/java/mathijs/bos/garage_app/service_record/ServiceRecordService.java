package mathijs.bos.garage_app.service_record;

import jakarta.persistence.EntityNotFoundException;
import mathijs.bos.garage_app.action.Action;
import mathijs.bos.garage_app.action.ActionRepository;
import mathijs.bos.garage_app.base_classes.BaseService;
import mathijs.bos.garage_app.car.Car;
import mathijs.bos.garage_app.car.CarRepository;
import mathijs.bos.garage_app.custom_action.CustomAction;
import mathijs.bos.garage_app.custom_action.CustomActionRepository;
import mathijs.bos.garage_app.customer.Customer;
import mathijs.bos.garage_app.issue.Issue;
import mathijs.bos.garage_app.issue.IssueRepository;
import mathijs.bos.garage_app.part.Part;
import mathijs.bos.garage_app.part.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceRecordService extends BaseService<ServiceRecord, ServiceRecordDTO, Long> {

    private final ServiceRecordRepository serviceRecordRepository;
    private final ServiceRecordMapper serviceRecordMapper;
    private final CarRepository carRepository;
    private final IssueRepository issueRepository;
    private final ActionRepository actionRepository;
    private final PartRepository partRepository;
    private final CustomActionRepository customActionRepository;

    @Autowired
    public ServiceRecordService(ServiceRecordRepository serviceRecordRepository, ServiceRecordMapper serviceRecordMapper, CarRepository carRepository, IssueRepository issueRepository, ActionRepository actionRepository, PartRepository partRepository, CustomActionRepository customActionRepository) {
        super(serviceRecordRepository, serviceRecordMapper);
        this.serviceRecordRepository = serviceRecordRepository;
        this.serviceRecordMapper = serviceRecordMapper;
        this.carRepository = carRepository;
        this.issueRepository = issueRepository;
        this.actionRepository = actionRepository;
        this.partRepository = partRepository;
        this.customActionRepository = customActionRepository;
    }

    @Override
    public ServiceRecordDTO create(ServiceRecordDTO dto) throws EntityNotFoundException {
        dto.setId(null);
        ServiceRecord serviceRecord = serviceRecordMapper.toEntity(dto);
        Car car = carRepository.findById(dto.getCarId()).orElseThrow(EntityNotFoundException::new);

        List<Issue> issues = dto.getIssueIdList().stream().map(
                id -> issueRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        ).toList();

        List<Action> actions = dto.getActionIdList().stream().map(
                id -> actionRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        ).toList();

        List<Part> parts = dto.getPartIdList().stream().map(
                id -> partRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        ).toList();

        List<CustomAction> customActions = dto.getCustomActionIdList().stream().map(
                id -> customActionRepository.findById(id).orElseThrow(EntityNotFoundException::new)
        ).toList();

        serviceRecord.setCar(car);
        serviceRecord.setIssues(issues);
        serviceRecord.setActions(actions);
        serviceRecord.setParts(parts);
        serviceRecord.setCustomActions(customActions);

        ServiceRecord saved = serviceRecordRepository.save(serviceRecord);

        return serviceRecordMapper.toDto(saved);
    }

    @Override
    public ServiceRecordDTO update(Long id, ServiceRecordDTO dto) throws EntityNotFoundException {

        Car car = carRepository.findById(dto.getCarId()).orElseThrow(EntityNotFoundException::new);

        List<Part> parts = dto.getPartIdList().stream().map(
                partId -> partRepository.findById(partId).orElseThrow(EntityNotFoundException::new)
        ).toList();

        List<Action> actions = dto.getActionIdList().stream().map(
                actionId -> actionRepository.findById(actionId).orElseThrow(EntityNotFoundException::new)
        ).toList();

        List<CustomAction> customActions = dto.getCustomActionIdList().stream().map(
                caId -> customActionRepository.findById(caId).orElseThrow(EntityNotFoundException::new)
        ).toList();

        List<Issue> issues = dto.getIssueIdList().stream().map(
                issueId -> issueRepository.findById(issueId).orElseThrow(EntityNotFoundException::new)
        ).toList();

        ServiceRecord updated = serviceRecordRepository.findById(id).map(
                serviceRecord -> {
                    serviceRecord.setId(dto.getId());
                    serviceRecord.setInspection(dto.getInspection());
                    serviceRecord.setRepair(dto.getRepair());
                    serviceRecord.setStatus(dto.getStatus());
                    serviceRecord.setReceipt(dto.getReceipt());
                    serviceRecord.setTotalCost(dto.getTotalCost());
                    serviceRecord.setCar(car);
                    serviceRecord.setParts(parts);
                    serviceRecord.setActions(actions);
                    serviceRecord.setCustomActions(customActions);
                    serviceRecord.setIssues(issues);

                    return serviceRecordRepository.save(serviceRecord);
                }
        ).orElseThrow(EntityNotFoundException::new);

        return serviceRecordMapper.toDto(updated);
    }

    public List<Customer> getCustomersWithCarsReadyForPickup(){
        List<Car> carsReadyForPickup = serviceRecordRepository.findByStatus(Status.READY_FOR_PICK_UP).stream()
                .map(ServiceRecord::getCar)
                .toList();

        return carsReadyForPickup.stream()
                .map(Car::getCustomer)
                .toList();
    }

    public Customer getCustomerDetails(Long serviceRecordId) {
        ServiceRecord serviceRecord = serviceRecordRepository.findById(serviceRecordId)
                .orElseThrow(() -> new ServiceRecordNotFoundException(serviceRecordId));

        return serviceRecord.getCar().getCustomer();
    }

    public List<Issue> getIssuesToFix(Long serviceRecordId){
        ServiceRecord serviceRecord = serviceRecordRepository.findById(serviceRecordId)
                .orElseThrow(() -> new ServiceRecordNotFoundException(serviceRecordId));

        return serviceRecord.getIssues().stream()
                .filter(Issue::getFixAgreement)
                .toList();
    }

    public ServiceRecord planInspection(Long id, LocalDateTime dateTime) {
        return serviceRecordRepository.findById(id)
                .map(serviceRecord -> {
                    serviceRecord.setInspection(dateTime);
                    return serviceRecordRepository.save(serviceRecord);
                }).orElseThrow(() -> new ServiceRecordNotFoundException(id));
    }

    public ServiceRecord planRepair(Long id, LocalDateTime dateTime) {
        return serviceRecordRepository.findById(id)
                .map(serviceRecord -> {
                    serviceRecord.setRepair(dateTime);
                    return serviceRecordRepository.save(serviceRecord);
                }).orElseThrow(() -> new ServiceRecordNotFoundException(id));
    }

    public ServiceRecord changeStatus(Long id, Status status) {
        return serviceRecordRepository.findById(id)
                .map(serviceRecord -> {
                    serviceRecord.setStatus(status);
                    return serviceRecordRepository.save(serviceRecord);
                }).orElseThrow(() -> new ServiceRecordNotFoundException(id));
    }


}
