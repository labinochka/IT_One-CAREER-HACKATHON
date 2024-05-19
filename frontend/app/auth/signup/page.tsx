import Link from "next/link";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

export default function SignupForm() {
  return (
    <div className="flex min-h-screen w-full flex-col justify-center items-center">
      <Card className="mx-auto max-w-sm">
        <CardHeader>
          <CardTitle className="text-xl">Регистрация</CardTitle>
          <CardDescription>
            Введите свои данные, чтобы создать аккаунт
          </CardDescription>
        </CardHeader>
        <CardContent>
          <div className="grid gap-4">
            <div className="grid grid-cols-2 gap-4">
              <div className="grid gap-2">
                <Label htmlFor="first-name">Имя</Label>
                <Input id="first-name" placeholder="Петр" required />
              </div>
              <div className="grid gap-2">
                <Label htmlFor="last-name">Фамилия</Label>
                <Input id="last-name" placeholder="Петров" required />
              </div>
            </div>
            <div className="grid gap-2">
              <Label htmlFor="email">Электронная почта</Label>
              <Input
                id="email"
                type="email"
                placeholder="m@example.com"
                required
              />
            </div>
            <div className="grid gap-2">
              <Label htmlFor="password">Пароль</Label>
              <Input id="password" type="password" />
            </div>
            <Button type="submit" className="w-full">
              Создать аккаунт
            </Button>
          </div>
          <div className="mt-4 text-center text-sm">
            Уже есть аккаунт?{" "}
            <Link href="/auth/signin" className="underline">
              Войти
            </Link>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}
