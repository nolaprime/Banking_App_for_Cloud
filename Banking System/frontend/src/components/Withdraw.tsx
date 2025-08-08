// Withdraw component
// Task checklist:
// - Build a form with inputs: accountNumber, amount
// - Validate: amount > 0
// - Read token from localStorage
// - On submit: POST /api/accounts/:accountNumber/withdraw with { amount }
// - Headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` }
// - On success: show confirmation and updated balance if returned
// - Handle errors (e.g., insufficient funds) and loading state
//
// Example (fetch usage):
// async function handleWithdraw(e) {
//   e.preventDefault();
//   const token = localStorage.getItem('jwtToken');
//   const res = await fetch(`/api/accounts/${accountNumber}/withdraw`, {
//     method: 'POST',
//     headers: { 'Content-Type': 'application/json', Authorization: `Bearer ${token}` },
//     body: JSON.stringify({ amount })
//   });
//   if (!res.ok) throw new Error(await res.text());
//   const data = await res.json();
// }

export default function Withdraw() {
  return (
    <div>
      {/* TODO: Implement Withdraw form (accountNumber, amount); call backend; show results/errors */}
    </div>
  );
} 