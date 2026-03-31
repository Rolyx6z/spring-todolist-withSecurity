package train.todolist.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterGroupForm {

    @NotBlank(message = "グループ名を入力してください")
    private String groupName;
}
