package com.rdhawane.recipemanagement.api.request;

import com.rdhawane.recipemanagement.config.ValidationConfig;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateIngredientRequest {
    @NotBlank(message = "{ingredient.notBlank}")
    @Size(max = ValidationConfig.MAX_LENGTH_NAME, message = "{ingredient.size}")
    @Pattern(regexp = ValidationConfig.PATTERN_NAME, message = "{ingredient.pattern}")
    @ApiModelProperty(notes = "The name of the ingredient", example = "Potato")
    private String name;

    public CreateIngredientRequest() {
    }

    public CreateIngredientRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
