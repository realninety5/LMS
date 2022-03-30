package org.zeep.library.domain.CatalogueDomain.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.zeep.library.DTO.BookDTO;
import org.zeep.library.model.BookEditionModel;
import org.zeep.library.model.BookModel;

import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class CatalogueResponse {
    private int responseCode;
    private String message;
    private Map<String, Set<BookDTO>> books;
}
