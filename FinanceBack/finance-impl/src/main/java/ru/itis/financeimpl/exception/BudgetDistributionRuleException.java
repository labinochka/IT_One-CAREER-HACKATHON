package ru.itis.financeimpl.exception;

public class BudgetDistributionRuleException extends NotFoundException{

    public BudgetDistributionRuleException() {
        super("Budget distribution rule not found");
    }
}
