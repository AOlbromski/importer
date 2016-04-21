package pl.com.importer.assembler.security;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import pl.com.importer.assembler.Assembler;
import pl.com.importer.dto.security.UserDTO;
import pl.com.importer.model.security.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class UserAssembler.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public class UserAssembler implements Assembler<User, UserDTO> {

    private ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public User assemblyToDbo(final UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public List<User> assemblyToDbo(final List<UserDTO> dto) {
        return dto.stream().map(this::assemblyToDbo).collect(Collectors.toList());
    }

    @Override
    public UserDTO assemblyToDto(final User dbo) {
        return modelMapper.map(dbo, UserDTO.class);
    }

    @Override
    public List<UserDTO> assemblyToDto(final List<User> dbo) {
        return dbo.stream().map(this::assemblyToDto).collect(Collectors.toList());
    }
}
