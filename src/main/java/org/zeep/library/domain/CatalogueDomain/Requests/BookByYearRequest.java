package org.zeep.library.domain.CatalogueDomain.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor @Setter @Getter
public class BookByYearRequest {
    @NotEmpty(message = "Please choose a year.")
    private String year;
}
