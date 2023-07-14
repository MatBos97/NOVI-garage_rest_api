package mathijs.bos.garage_app.service_record;

import mathijs.bos.garage_app.base_classes.BaseRepository;

import java.util.List;

public interface ServiceRecordRepository extends BaseRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByStatus(Status status);
}