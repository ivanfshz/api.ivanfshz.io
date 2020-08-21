package io.ivanfshz.api.resume.service;

import io.ivanfshz.api.resume.properties.ResumeProperties;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class ResumeService {

    private final ResumeProperties resumeProperties;
    Logger logger = LoggerFactory.getLogger(ResumeService.class);

    @Autowired
    public ResumeService(ResumeProperties resumeProperties) {
        this.resumeProperties = resumeProperties;
    }

    public Resource download(final File file) {
        return getByteArrayResource(getPath(file));
    }

    public File getFile(final String name,
                        final String format) {
        final String pathName = resumeProperties.getPath()
                .concat(name)
                .concat(".")
                .concat(format);
        return new File(pathName);
    }

    private Resource getByteArrayResource(final Path path) {
        ByteArrayResource byteArrayResource = null;
        try {
            final byte[] bytes = Files.readAllBytes(path);
            byteArrayResource = new ByteArrayResource(bytes);
        } catch (IOException e) {
            logger.error("Error while trying to get file, path {}. Error: {}", path, e.getMessage());
        }
        return byteArrayResource;
    }

    private Path getPath(final File file) {
        return Paths.get(file.getAbsolutePath());
    }
}