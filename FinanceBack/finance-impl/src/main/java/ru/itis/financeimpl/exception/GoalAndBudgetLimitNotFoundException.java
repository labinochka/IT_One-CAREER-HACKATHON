package ru.itis.financeimpl.exception;

public class GoalAndBudgetLimitNotFoundException extends NotFoundException{

    public GoalAndBudgetLimitNotFoundException() {
        super("Goal and Budget limit not found");
    }
}
