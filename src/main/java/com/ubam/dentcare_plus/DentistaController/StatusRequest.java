package com.ubam.dentcare_plus.DentistaController;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusRequest {
    Integer Citaid;
    Integer statusId;
}
