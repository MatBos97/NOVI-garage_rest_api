package mathijs.bos.garage_app;

import mathijs.bos.garage_app.part.Part;
import mathijs.bos.garage_app.service_record.ServiceRecordDTO;
import mathijs.bos.garage_app.service_record.Status;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@SpringBootTest
class GarageAppApplicationTests {

    @Test
    void contextLoads() {

        LocalDateTime time = LocalDateTime.now();
        LocalDateTime repairTime = LocalDateTime.now();
        Status status = Status.WAITING;
        Byte[] receipt = new Byte[0];
        Currency totalCost = Currency.getInstance("EUR");
        Long carId = 1L;
        List<Part> parts = List.of(new Part(1L, "band", totalCost, 4));

    }

}
