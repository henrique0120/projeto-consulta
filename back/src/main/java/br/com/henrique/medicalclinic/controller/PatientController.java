package br.com.henrique.medicalclinic.controller;

import br.com.henrique.medicalclinic.controller.request.SavePatientRequest;
import br.com.henrique.medicalclinic.controller.request.UpdatePatientRequest;
import br.com.henrique.medicalclinic.controller.response.ListPatientResponse;
import br.com.henrique.medicalclinic.controller.response.PatientDetailResponse;
import br.com.henrique.medicalclinic.controller.response.SavePatientResponse;
import br.com.henrique.medicalclinic.controller.response.UpdatePatientResponse;
import br.com.henrique.medicalclinic.mapper.IPatientMapper;
import br.com.henrique.medicalclinic.service.IPatientService;
import br.com.henrique.medicalclinic.service.query.IPatientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

@RestController
@RequestMapping("patients")
@AllArgsConstructor
public class PatientController {

    private final IPatientService service;
    private final IPatientQueryService queryService;
    private final IPatientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SavePatientResponse save(@RequestBody @Valid final SavePatientRequest request){
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("{id}")
    UpdatePatientResponse update(@PathVariable final long id, @RequestBody @Valid final UpdatePatientRequest request){
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final long id){
        service.delete(id);
    }

    @GetMapping("{id}")
    PatientDetailResponse findById(@PathVariable final long id){
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @GetMapping
    List<ListPatientResponse> list(){
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }

}
