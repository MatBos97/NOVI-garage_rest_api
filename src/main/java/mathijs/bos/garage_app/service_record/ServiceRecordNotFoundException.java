package mathijs.bos.garage_app.service_record;

import jakarta.persistence.EntityNotFoundException;

public class ServiceRecordNotFoundException extends EntityNotFoundException {

    public ServiceRecordNotFoundException(Long id) {
        super("No service record with id:" + id + " was found");
    }
}
