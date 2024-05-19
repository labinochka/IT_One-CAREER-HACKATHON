"use client";

import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Header } from "../page";
import { Button } from "@/components/ui/button";
import { api } from "@/shared/api";
import { useState } from "react";

export default function Component() {
  const [goal, setGoal] = useState<number>(0);
  const [budgetLimit, setBudgetLimit] = useState<number>(0);
  const [expenseGoal, setExpenseGoal] = useState<number>(0);
  const [startDate, setStartDate] = useState<string>("");
  const [endDate, setEndDate] = useState<string>("");

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    try {
      api.post("/goalAndBudgetLimit", {
        goal,
        budgetLimit,
        expenseGoal,
        startDate,
        endDate,
      });
    } catch (error) {
      console.error(error);
    }
  };
  return (
    <div>
      <Header />
      <main className="max-w-screen-md mx-auto w-full p-4">
        <div className="relative hidden flex-col items-start gap-8 md:flex">
          <form
            onSubmit={handleSubmit}
            className="grid w-full items-start gap-6"
          >
            <h2 className="scroll-m-20 text-3xl font-semibold tracking-tight first:mt-0">
              Создать цель и лимит бюджета
            </h2>
            <fieldset className="grid gap-6 rounded-lg border p-4">
              <div className="grid gap-3">
                <Label htmlFor="goal">Цель заработка</Label>
                <Input
                  onChange={(e) => setGoal(Number(e.target.value))}
                  id="goal"
                  type="number"
                />
              </div>
              <div className="grid gap-3">
                <Label htmlFor="expense">Цель расходов</Label>
                <Input
                  onChange={(e) => setExpenseGoal(Number(e.target.value))}
                  id="expense"
                  type="number"
                />
              </div>
              <div className="grid gap-3">
                <Label htmlFor="budget-limit">Лимит бюджета</Label>
                <Input
                  onChange={(e) => setExpenseGoal(Number(e.target.value))}
                  id="bugdet-limit"
                  type="number"
                />
              </div>
              <div className="grid grid-cols-2 gap-4">
                <div className="grid gap-3">
                  <Label htmlFor="start-date">Дата начала</Label>
                  <Input id="start-date" type="date" />
                </div>
                <div className="grid gap-3">
                  <Label htmlFor="end-date">Дата окончания</Label>
                  <Input id="end-date" type="date" />
                </div>
              </div>
              <Button type="submit">Создать</Button>
            </fieldset>
          </form>
        </div>
      </main>
    </div>
  );
}
