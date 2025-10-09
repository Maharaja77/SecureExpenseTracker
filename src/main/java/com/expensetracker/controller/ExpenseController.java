package com.expensetracker.controller;


import com.expensetracker.model.Expense;
import com.expensetracker.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

}
