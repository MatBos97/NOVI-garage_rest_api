package mathijs.bos.garage_app.service_record;

public class ServiceRecordNotFoundException extends Exception{

    public ServiceRecordNotFoundException(Long id) {
        super("No service record with id:" + id + " was found");
    }
}
