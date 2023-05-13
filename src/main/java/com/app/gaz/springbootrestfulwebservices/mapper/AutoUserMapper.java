package com.app.gaz.springbootrestfulwebservices.mapper;

import com.app.gaz.springbootrestfulwebservices.dto.UserDto;
import com.app.gaz.springbootrestfulwebservices.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
    /**
     * 1. para obtener la implementación de esta interfaz, podemos usar la clase de utilidad mappers
     * 2. Dentro de esta interfaz, definamos el punto de entrada aquí.
     * 3. MAPPER: MapStruct proporciona una fábrica de Mappers,
     *    (public class Mappers) Tiene un método getMapper y simplemente pasa AutoUserMapper.class
     * 4. Esto proporcionará la implementación de esta interfaz en el momento de la compilación
     * 5. Y a continuación, podemos usar esta instancia del asignador para llamar a estos métodos de asignación
     */
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    //Convertiremos la entidad User en UserDto,
    UserDto mapToUserDto(User user);

    //onvertiremos UserDto en User
    User mapTouser(UserDto userDto);
}

/**
 * - MapStruct creará la implementación de estos métodos en un momento de compilación
 * - No tenemos que escribir un código para implementar estos métodos.
 * - MapStruct creará un código para implementar estos metodos en tiempo de compilaccion.
 * - Lo más importante aquí es para mapear un objeto en otro objeto, tanto el objeto como el objeto,
 * deben tener los mismos nombres de campo
 * Así que a veces los nombres de los campos son diferentes y para mapear estos campos podemos usar @Mapping anotación
 *
 * @Mapping(source="email", terget="emailAdderres")
 * <p>
 * para usar estos métodos de mapeo, necesitamos crear un objeto, no podemos crear un objeto de esta interfaz
 * Deberíamos tener una clase de implementación para que MapStruct proporcione implementación para esta interfaz,
 * para obtener la implementación de esta interfaz, podemos usar la clase de utilidad mappers
 * <p>
 * AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
 * esto proporcionará la implementación de esta interfaz en el momento de la compilación,
 */