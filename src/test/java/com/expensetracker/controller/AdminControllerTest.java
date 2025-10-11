package com.expensetracker.controller;

import com.expensetracker.model.Expense;
import com.expensetracker.model.User;
import com.expensetracker.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    private User user1;
    private User user2;
    private Expense expense1;
    private Expense expense2;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setId(1L);
        user1.setUsername("maharaja");

        user2 = new User();
        user2.setId(2L);
        user2.setUsername("alex");

        expense1 = new Expense();
        expense1.setId(100L);
        expense1.setDescription("Lunch");
        expense1.setAmount(BigDecimal.valueOf(200.0));
        expense1.setDate(LocalDate.of(2025, 10, 10));
        expense1.setUser(user1);

        expense2 = new Expense();
        expense2.setId(101L);
        expense2.setDescription("Dinner");
        expense2.setAmount(BigDecimal.valueOf(300.0));
        expense2.setDate(LocalDate.of(2025, 10, 11));
        expense2.setUser(user1);
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> mockUsers = Arrays.asList(user1, user2);
        when(adminService.getAllUsers()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/admin/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].username").value("maharaja"))
                .andExpect(jsonPath("$[1].username").value("alex"));
    }

    @Test
    void testGetUserExpenses() throws Exception {
        List<Expense> mockExpenses = Arrays.asList(expense1, expense2);
        when(adminService.getUserExpenses(1L)).thenReturn(mockExpenses);

        mockMvc.perform(get("/api/admin/expenses")
                .param("userId", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].description").value("Lunch"))
                .andExpect(jsonPath("$[1].description").value("Dinner"));
    }
}
