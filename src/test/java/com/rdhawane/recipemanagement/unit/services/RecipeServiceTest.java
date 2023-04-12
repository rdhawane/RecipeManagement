package com.rdhawane.recipemanagement.unit.services;

import com.rdhawane.recipemanagement.api.request.CreateRecipeRequest;
import com.rdhawane.recipemanagement.api.request.RecipeSearchRequest;
import com.rdhawane.recipemanagement.api.request.UpdateRecipeRequest;
import com.rdhawane.recipemanagement.config.MessageProvider;
import com.rdhawane.recipemanagement.exceptions.NotFoundException;
import com.rdhawane.recipemanagement.models.Recipe;
import com.rdhawane.recipemanagement.repositories.RecipeRepository;
import com.rdhawane.recipemanagement.services.IngredientService;
import com.rdhawane.recipemanagement.services.RecipeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {
    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientService ingredientService;

    @Mock
    private MessageProvider messageProvider;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    public void test_createRecipe_successfully() {
        CreateRecipeRequest request = new CreateRecipeRequest("pasta", "OTHER", 4, null, "instructions");


        Recipe response = new Recipe();
        response.setName("Name");
        response.setInstructions("instructions");
        response.setNumberOfServings(4);

        when(recipeRepository.save(any(Recipe.class))).thenReturn(response);

        Integer id = recipeService.createRecipe(request);

        assertThat(id).isSameAs(response.getId());
    }

    @Test
    public void test_updateRecipe_successfully() {
        Recipe response = new Recipe();
        response.setName("Name");
        response.setType("OTHER");
        response.setNumberOfServings(4);
        response.setId(5);

        UpdateRecipeRequest request = new UpdateRecipeRequest(1, "pasta", "OTHER", 4, null, "instructions");

        when(recipeRepository.save(any(Recipe.class))).thenReturn(response);
        when(recipeRepository.findById(anyInt())).thenReturn(Optional.of(response));

        recipeService.updateRecipe(request);
    }

    @Test(expected = NotFoundException.class)
    public void test_updateRecipe_notFound() {
        UpdateRecipeRequest request = new UpdateRecipeRequest(1, "pasta", "OTHER", 4, null, "instructions");

        when(recipeRepository.findById(anyInt())).thenReturn(Optional.empty());

        recipeService.updateRecipe(request);
    }

    @Test
    public void test_deleteRecipe_successfully() {
        when(recipeRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(recipeRepository).deleteById(anyInt());

        recipeService.deleteRecipe(1);
    }

    @Test(expected = NotFoundException.class)
    public void test_deleteRecipe_notFound() {
        when(recipeRepository.existsById(anyInt())).thenReturn(false);

        recipeService.deleteRecipe(1);
    }

    @Test(expected = NotFoundException.class)
    public void test_findBySearchCriteria_notFound() {
        RecipeSearchRequest recipeSearchRequest = mock(RecipeSearchRequest.class);
        recipeService.findBySearchCriteria(recipeSearchRequest,0,10);
    }

}