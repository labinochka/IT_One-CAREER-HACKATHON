package ru.itis.financeapi.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "BudgetDistributionRuleApi")
@RequestMapping("api/v1/budgetDistributionRule")
public interface BudgetDistributionRuleApi {
    void create();

    void update();

    // ToDO: find

    void delete();
}
