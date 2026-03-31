package train.todolist.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IdGroupForm {
    @NotNull(message = "グループを選択してください")
    private Integer id;
}
