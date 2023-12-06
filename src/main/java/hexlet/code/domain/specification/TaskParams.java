package hexlet.code.domain.specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskParams {
    private String titleCont;
    private Long assigneeId;
    private String status;
    private Long labelId;
}
