package mathijs.bos.garage_app.base_classes;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class BaseDTO implements Serializable {

    private Long id;
}
