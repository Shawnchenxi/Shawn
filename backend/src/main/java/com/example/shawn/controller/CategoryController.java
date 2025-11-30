package com.example.shawn.controller;

import com.example.shawn.dto.ApiResponse;
import com.example.shawn.entity.Category;
import com.example.shawn.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<Category>> list() {
        return ApiResponse.ok(categoryService.list());
    }

    @PostMapping
    public ApiResponse<Category> create(@RequestBody @Valid Category category) {
        return ApiResponse.ok("创建成功", categoryService.create(category));
    }
}
