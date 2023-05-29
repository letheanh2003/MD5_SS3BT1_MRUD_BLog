package rikkei.academy.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import rikkei.academy.model.Category;



public interface ICategoryRepository extends PagingAndSortingRepository<Category, Integer> {

}

