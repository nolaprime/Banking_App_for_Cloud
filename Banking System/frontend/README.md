## Banking System Frontend (Minimal Student Scaffold)

This is a deliberately minimal starting point. Your job is to build UI for:
- Authentication: Login
- Authentication: Signup
- Accounts: Deposit
- Accounts: Withdraw
- Accounts: Transactions (history or recent activity)

Only a few files are present so you can practice wiring everything yourself.

### Run locally
1. Start the Spring backend at `http://localhost:8080`.
2. In `frontend/`:
   ```bash
   npm install
   npm run dev
   ```
3. Open the dev URL shown in the terminal (usually `http://localhost:5173`).

Notes:
- The Vite dev server is configured with a proxy to `http://localhost:8080` for paths starting with `/api` (see `vite.config.ts`).
- For auth-protected endpoints, send `Authorization: Bearer <token>`.

### Backend endpoints you will use
- Signup: `POST /api/user/signup` with `{ email, plainPassword }` → responds 200 OK or 400 with error string
- Login: `POST /api/user/login` with `{ email, plainPassword }` → responds `{ jwtToken: string }`
- Deposit: `POST /api/accounts/:accountNumber/deposit` with `{ amount: number }` → responds with updated account (backend may return DTO or message)
- Withdraw: `POST /api/accounts/:accountNumber/withdraw` with `{ amount: number }`
- Transactions (if available):
  - Option A: `GET /api/accounts/:accountNumber` and display `transactions` array from the returned account DTO (if populated)
  - Option B: If `/api/accounts/:accountNumber/transactions` becomes available, use it directly. The provided backend `TransactionController` returns 404 right now, so prefer Option A.

---

### What you must build (granular checklist)

- App scaffolding
  - [ ] Decide on navigation: simple buttons to switch views OR add React Router yourself
  - [ ] Render the components from `src/components/` (Login, Signup, Deposit, Withdraw, Transactions)
  - [ ] Pass down needed callbacks/props (e.g., a `setToken` function) from `App.tsx`

- Auth: Login
  - [ ] Build a form with fields: `email`, `plainPassword`
  - [ ] Validate: email shape; password length (>= 6)
  - [ ] On submit: call `POST /api/user/login` with JSON body
  - [ ] On success: store `jwtToken` in `localStorage` under key `jwtToken`
  - [ ] Call back to parent (e.g., `onLogin(token)`)
  - [ ] Handle loading (disable submit) and errors (show message from server or fallback)

- Auth: Signup
  - [ ] Build a form with `email`, `plainPassword`
  - [ ] Validate: email shape; password complexity if desired
  - [ ] On submit: call `POST /api/user/signup`
  - [ ] On success: Option A: auto-login by calling Login; Option B: redirect/toggle to Login UI
  - [ ] Handle loading and errors

- Accounts: Deposit
  - [ ] Build a form with `accountNumber`, `amount`
  - [ ] Validate: amount > 0
  - [ ] Read JWT from `localStorage` and include `Authorization: Bearer <token>` header
  - [ ] Call `POST /api/accounts/:accountNumber/deposit` with JSON body
  - [ ] Show success state (message, updated balance if returned)
  - [ ] Handle loading and errors

- Accounts: Withdraw
  - [ ] Same as Deposit but call `POST /api/accounts/:accountNumber/withdraw`
  - [ ] Handle insufficient funds / validation messages from server

- Accounts: Transactions
  - [ ] Build a small UI with `accountNumber` input and a "Load" button
  - [ ] Strategy A: call `GET /api/accounts/:accountNumber` and display `transactions` from the response DTO
  - [ ] Render a list: `transactionId`, `transactionType`, `amount`, `transactionDate`, `oldBalance`, `newBalance`
  - [ ] Add basic formatting and empty states

- Error handling & UX
  - [ ] Show both field-level and form-level errors
  - [ ] Disable submit buttons while requests are in-flight
  - [ ] Use `try/catch` and display server response text when available

---

### Example snippets (using fetch)

Use these as reference inside your components. You may use `fetch` (built-in) or add `axios` yourself.

```ts
// POST /api/user/login
async function login(email: string, plainPassword: string): Promise<string> {
  const res = await fetch('/api/user/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, plainPassword })
  });
  if (!res.ok) {
    // Backend often returns text for errors
    const message = await res.text();
    throw new Error(message || 'Login failed');
  }
  const data = await res.json(); // { jwtToken: string }
  return data.jwtToken as string;
}
```

```ts
// POST /api/user/signup
async function signup(email: string, plainPassword: string): Promise<void> {
  const res = await fetch('/api/user/signup', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, plainPassword })
  });
  if (!res.ok) throw new Error(await res.text());
}
```

```ts
// POST /api/accounts/:accountNumber/deposit
async function deposit(accountNumber: string, amount: number): Promise<any> {
  const token = localStorage.getItem('jwtToken');
  const res = await fetch(`/api/accounts/${accountNumber}/deposit`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    },
    body: JSON.stringify({ amount })
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json(); // Could be updated account or message depending on backend
}
```

```ts
// POST /api/accounts/:accountNumber/withdraw
async function withdraw(accountNumber: string, amount: number): Promise<any> {
  const token = localStorage.getItem('jwtToken');
  const res = await fetch(`/api/accounts/${accountNumber}/withdraw`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`
    },
    body: JSON.stringify({ amount })
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json();
}
```

```ts
// GET /api/accounts/:accountNumber → read transactions from account DTO
async function getAccount(accountNumber: string): Promise<any> {
  const token = localStorage.getItem('jwtToken');
  const res = await fetch(`/api/accounts/${accountNumber}`, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
  if (!res.ok) throw new Error(await res.text());
  return res.json(); // { customerId, accountNumber, accountType, balance, transactions? }
}
```

---

### Suggested order of implementation
1. Signup (simpler), then Login
2. After login, verify JWT by calling a protected endpoint and checking 401 vs 200
3. Implement Deposit and Withdraw forms
4. Implement Transactions view via `GET /api/accounts/:accountNumber`

### Tips
- Keep components small. One component = one form is fine.
- Extract repeated logic (e.g., header with Authorization) into helpers if you want, but keep it simple.
- Log API responses while building to understand shapes.
- Don’t forget to handle negative cases (invalid credentials, invalid amounts, missing account). 