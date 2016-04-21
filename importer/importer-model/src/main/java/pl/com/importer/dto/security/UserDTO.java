package pl.com.importer.dto.security;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Class UserDTO
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    @Setter(AccessLevel.NONE)
    private String login;

    @NotNull
    @Size(min = 5, max = 100)
    @Setter(AccessLevel.NONE)
    private String password;

    @Size(max = 50)
    @Setter(AccessLevel.NONE)
    private String firstName;

    @Size(max = 50)
    @Setter(AccessLevel.NONE)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    @Setter(AccessLevel.NONE)
    private String email;

    @Size(min = 2, max = 5)
    @Setter(AccessLevel.NONE)
    private String langKey;

    @Setter(AccessLevel.NONE)
    private Set<String> roles = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @NotNull
    private String pesel;
}
