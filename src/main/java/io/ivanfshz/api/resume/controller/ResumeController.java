package io.ivanfshz.api.resume.controller;

import io.ivanfshz.api.resume.exception.ResumeException;
import io.ivanfshz.api.resume.mapper.ContentTypeMapper;
import io.ivanfshz.api.resume.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Optional;

@RestController
public class ResumeController {

    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping(value = "/resume")
    public ResponseEntity<Resource> download(@RequestParam(value = "name", defaultValue = "resume") String name,
                                             @RequestParam(value = "format", defaultValue = "pdf") String format) {
        File file = resumeService.getFile(name, format);
        return Optional.ofNullable(resumeService.download(file))
                .map(resource -> {
                    return ResponseEntity.ok()
                            .contentLength(file.length())
                            .contentType(ContentTypeMapper.toMediaType(format))
                            .body(resource);
                }).orElseThrow(() -> new ResumeException("Error while tryign to get document: " + name + "." + format));
    }
}
