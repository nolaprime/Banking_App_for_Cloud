// Transactions component
// Task checklist:
// - Build a small UI with an input for accountNumber and a "Load" button
// - Strategy A (recommended): GET /api/accounts/:accountNumber and read `transactions` from response
// - Render list items: transactionId, transactionType, amount, transactionDate, oldBalance, newBalance
// - Add loading and error states, and handle empty list
// - You may format dates using `new Date(transactionDate).toLocaleString()`
//
// Example (fetch usage):
// async function loadAccount() {
//   const token = localStorage.getItem('jwtToken');
//   const res = await fetch(`/api/accounts/${accountNumber}`, {
//     headers: { Authorization: `Bearer ${token}` }
//   });
//   if (!res.ok) throw new Error(await res.text());
//   const data = await res.json();
//   // data.transactions may be undefined; handle that case
//   setTransactions(data.transactions ?? []);
// }

export default function Transactions() {
  return (
    <div>
      {/* TODO: Implement Transactions list for an account; show loading/errors/empty state */}
    </div>
  );
} 