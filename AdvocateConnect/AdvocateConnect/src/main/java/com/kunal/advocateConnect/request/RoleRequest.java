package com.kunal.advocateConnect.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kunal.advocateConnect.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    private Set<String> role;

}
