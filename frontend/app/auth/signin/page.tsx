"use client";

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
import axios from "axios";
import { useState } from "react";
import { useRouter } from "next/navigation";

export default function SigninPage() {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [error, setError] = useState<boolean>();
  const router = useRouter()

  return (
    <div className="min-h-screen flex justify-center items-center w-full flex-col">
      <Card className="mx-auto max-w-sm">
        <CardHeader>
          <CardTitle className="text-2xl">Вход</CardTitle>
          <CardDescription>
            Введите свои данные, чтобы войти в аккаунт
          </CardDescription>
        </CardHeader>
        <CardContent>
          <form
            className="grid gap-4"
            onSubmit={async (e) => {
              try {
                e.preventDefault();
                const res = await axios.post("http://localhost:8080/sign-in", {
                  email,
                  password,
                });

                localStorage.setItem("access-token", res.data?.accessToken);
                router.push('/')
              } catch (e) {
                setError(true);
                console.error(e);
              }
            }}
          >
            <div className="grid gap-2">
              <Label htmlFor="email">Электронная почта</Label>
              <Input
                id="email"
                type="email"
                placeholder="m@example.com"
                required
                value={email ?? ""}
                onInput={(e) => setEmail((e.target as any).value)}
              />
            </div>
            <div className="grid gap-2">
              <div className="flex items-center">
                <Label htmlFor="password">Пароль</Label>
                <Link
                  href="#"
                  className="ml-auto inline-block text-sm underline"
                >
                  Забыли пароль?
                </Link>
              </div>
              <Input
                id="password"
                type="password"
                required
                value={password ?? ""}
                onInput={(e) => setPassword((e.target as any).value)}
              />
            </div>
            <Button type="submit" className="w-full">
              Войти
            </Button>
          </form>

          {error && (
            <div className="text-center text-sm mt-4 text-red-500">
              Ошибка авторизации
            </div>
          )}

          <div className="mt-4 text-center text-sm">
            Нет аккаунта?{" "}
            <Link href="/auth/signup" className="underline">
              Зарегистрироваться
            </Link>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}
