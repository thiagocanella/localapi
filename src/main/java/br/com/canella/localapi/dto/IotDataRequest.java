package br.com.canella.localapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IotDataRequest {
    private String dataName;
    private String dataRegistry;
}
