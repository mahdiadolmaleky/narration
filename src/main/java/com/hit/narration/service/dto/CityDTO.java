package com.hit.narration.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CityDTO implements Serializable {

    private Long id;

    private String title;

    private String callingCode;

    private Long provinceId;

    private Long accentId;

    private Long regionId;
}
