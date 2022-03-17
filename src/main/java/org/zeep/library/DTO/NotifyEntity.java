package org.zeep.library.DTO;


import lombok.*;

import java.util.UUID;

@AllArgsConstructor @Data @NoArgsConstructor
public class NotifyEntity {
    private UUID memberId;
    private UUID bookId;
}
