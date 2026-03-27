package com.ubam.dentcare_plus.dto.dentista;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialDTO {
    Integer userId;
    Integer citaId;
    String diagnostico;
    String tratamiento;
    String recomendacion;
    List<MultipartFile> archivos;
}
