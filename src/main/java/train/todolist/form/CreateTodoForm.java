package train.todolist.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class CreateTodoForm {
    @NotNull(message = "件名を入力してください")
    @NotBlank(message = "件名を入力してください")
    private String todo;

    @NotNull(message = "日付を入力してください")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskDate;

    private Integer id;
}
