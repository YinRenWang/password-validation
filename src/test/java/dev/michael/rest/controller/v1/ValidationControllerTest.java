package dev.michael.rest.controller.v1;

import dev.michael.PasswordValidationApplication;
import dev.michael.core.model.ValidationRequest;
import dev.michael.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(classes = {PasswordValidationApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ValidationControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void testNotRequest() throws Exception {
        String validationRequest = "";
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(validationRequest))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testRequestIsNull() throws Exception {
        String validationRequest = "{}";
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(validationRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("password is null or Empty")));
    }

    @Test
    public void testRequestIsEmpty() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("     ");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("password is null or Empty")));
    }

    @Test
    public void testRequestLengthTooShort() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("ab12");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("length is too short at least 5")));
    }

    @Test
    public void testRequestLengthTooLong() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("abcde1234567890");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("length is too long at most 12")));
    }

    @Test
    public void testRequestContainsSequenceRepeat() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("12ababcbaba");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("contain any sequence of characters immediately followed by the same sequence (abab)")));
    }


    @Test
    public void testRequestLoseDigitsAndContainsSequenceRepeat() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("snaaa");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("characters not meet the requirements of the rules not enough Digits")))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("contain any sequence of characters immediately followed by the same sequence (aa)")));
    }

    @Test
    public void testRequestContainsIllegalCharacter() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("~a123456789");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("contains invalid characters")));
    }

    @Test
    public void testRequestTooShortAndLoseLowCaseAndContainsSequenceRepeat() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("1211");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("length is too short at least 5")))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("characters not meet the requirements of the rules not enough LowerCase")))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg", hasItem("contain any sequence of characters immediately followed by the same sequence (11)")));
    }

    @Test
    public void testVerified1() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("a123456789");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isEmpty());
    }

    @Test
    public void testVerified2() throws Exception {
        ValidationRequest validationRequest = new ValidationRequest("ab123ab");
        String json = JsonUtil.getObjectJsonString(validationRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/validation")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("valid").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("errorMsg").isEmpty());
    }
}