package train.todolist.account.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountForm {
    @NotBlank(message = "ユーザー名が未入力です")
    private String username;

    @NotBlank(message = "パスワードが未入力です")
    private String password;
}
