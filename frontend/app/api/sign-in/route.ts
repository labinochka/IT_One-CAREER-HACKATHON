import { NextRequest } from "next/server";

export async function POST(req: NextRequest, next: () => void) {
  const res = await req.json();
  const { email, password } = res;
  return Response.json(res);
}
