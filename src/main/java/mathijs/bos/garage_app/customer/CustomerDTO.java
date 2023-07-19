package mathijs.bos.garage_app.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mathijs.bos.garage_app.base_classes.BaseDTO;

import java.util.List;

@Getter
@Setter
@Builder
public class CustomerDTO extends BaseDTO {

    private String name;
    private String phone;
    private List<Long> carIdList;

}
