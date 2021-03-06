package org.zeep.library.domain.BookDomain.Requests.Manage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter @Setter @AllArgsConstructor @Builder
public class ItemAddRequest {
    @NotEmpty(message = "Please enter the editionId")
    private UUID editionId;
}
