package com.rdhawane.recipemanagement.search.filter;

import com.rdhawane.recipemanagement.models.Recipe;
import com.rdhawane.recipemanagement.search.SearchOperation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface SearchFilter  {
    boolean couldBeApplied(SearchOperation opt);
    Predicate apply(CriteriaBuilder cb, String filterKey, String filterValue, Root<Recipe> root, Join<Object, Object> subRoot);
}