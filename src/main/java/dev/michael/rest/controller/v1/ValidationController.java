package dev.michael.rest.controller.v1;

import dev.michael.core.model.ValidationRequest;
import dev.michael.core.model.ValidationResponse;
import dev.michael.core.service.ValidationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/rest/v1")
public class ValidationController {

    private static final Logger log = LoggerFactory.getLogger(ValidationController.class);

    @Autowired
    private ValidationService validationService;

    @PostMapping(value = "/validation", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "validation request")
    public ResponseEntity<ValidationResponse> validation(@ApiParam(value = "request", required = true) @RequestBody ValidationRequest request) {
        ValidationResponse response = null;
        try {
            response = validationService.validation(request);
        } catch (Exception e) {
            log.info("validation ERROR:{}", e);
        }
        return ResponseEntity.ok(response);
    }
}
