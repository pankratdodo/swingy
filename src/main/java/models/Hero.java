package models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 30, message = "Name can have from 3 to 30 symbols")
    @Pattern(regexp = "\\w+", message = "Name can have only letters, numbers and '_' symbol")
    String name;

    String clas;
}
