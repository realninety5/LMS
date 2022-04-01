package org.zeep.library.DTO;


import lombok.*;

import java.util.Date;

@AllArgsConstructor @Data @NoArgsConstructor @Builder
public class NotifyEntity {
    private String bookName;
    private String reservedBy;
    private Date date;
    private String email;
}
