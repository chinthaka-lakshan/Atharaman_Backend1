package com.example.AtharamanBackend1.service;

import com.example.AtharamanBackend1.dto.LocationDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LocationService {
    LocationDto createLocation(LocationDto locationDto, MultipartFile[] images)throws IOException;
    List<LocationDto> getAllLocations();
    LocationDto getLocationById(Long id);
    LocationDto updateLocationById(Long id, LocationDto locationDto,MultipartFile[] images)throws IOException;
    void deleteLocationById(Long id);

}
