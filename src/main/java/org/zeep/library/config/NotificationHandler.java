package org.zeep.library.config;

import lombok.*;
import org.zeep.library.DTO.NotifyEntity;

@Data @AllArgsConstructor
public class NotificationHandler {
    public boolean notify(NotifyEntity entity) {
        return true;
    }
}
