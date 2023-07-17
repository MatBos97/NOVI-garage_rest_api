package mathijs.bos.garage_app.issue;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IssueDTO {

    private Long id;
    private String description;
    private Boolean fixAgreement;
}
