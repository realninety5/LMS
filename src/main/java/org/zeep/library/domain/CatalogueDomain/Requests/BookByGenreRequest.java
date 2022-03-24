package org.zeep.library.domain.CatalogueDomain.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.zeep.library.ExceptionsAndValidators.validators.ValueOfEnum;
import org.zeep.library.enums.Genre;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Setter
@Getter
public class BookByGenreRequest {
    @NotEmpty(message = "Please choose a genre.")
    @ValueOfEnum(enumClass = Genre.class)
    private Genre genre;
}
