package org.zeep.library.domain.CatalogueDomain.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.zeep.library.Exceptions.validators.ValueOfEnum;
import org.zeep.library.enums.Genre;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class BookByGenreRequest {
    @NotEmpty(message = "Please choose a genre.")
    @ValueOfEnum(enumClass = Genre.class)
    private Genre genre;
}
